package com.cg.onlinewallet.dao;




import com.cg.onlinewallet.util.DBUtil;
import com.cg.onlinewallet.dto.Transaction;

import com.cg.onlinewallet.dto.WalletAccount;

import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.exception.MyException;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import java.util.HashMap;

import java.util.List;

import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class WalletUserDaoImpl implements WalletUserDao {

	

	private static Map<BigInteger,WalletUser> users = new HashMap<BigInteger, WalletUser>();

	private WalletAccountDao accDao= new WalletAccountDaoImpl();
	private static Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private static Logger myLogger;
	static{
    	
  	  Properties props = System.getProperties();
  	  String userDir= props.getProperty("user.dir")+"/src/main/resources/";
  	  System.out.println("Current working directory is " +userDir);
  	  PropertyConfigurator.configure(userDir+"log4j.properties");
		myLogger=Logger.getLogger("DBUtil.class");
		}
	
	static {
		try {
			connection= DBUtil.getConnection();
		} catch (MyException e) {
			myLogger.error("Connection not obtained at EmployeeDao : e");
			//System.out.print("Connection Not Obtained at EmployeeDao");
		}
	}




	public WalletUser addWalletUser(WalletUser user) {

		String sql="insert into user(user_name,user_password,user_phone,account_id) values(?,?,?,?)";

		users.put(user.getUserId(), user);

		WalletAccount account=accDao.addAccount(user.getAccount());
		
		try {
			ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getPhoneNo());
			ps.setLong(4, account.getAccountNo().longValue());
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if(rs.next()) {
				user.setUserId(BigInteger.valueOf(rs.getLong(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at addUser Dao method : "+e);
				}
			}
		}

		return user;

	}



	public Map<BigInteger, WalletUser> showUsers() {
		
		Map<BigInteger,WalletUser> users=new HashMap<BigInteger, WalletUser>();
		String sql="select * from user";
		try {
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				WalletUser user = new WalletUser();
				user.setUserId(BigInteger.valueOf(rs.getLong(1)));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setPhoneNo(rs.getString("user_phone"));
				WalletAccount account = accDao.searchAccount(BigInteger.valueOf(rs.getLong("account_id")));
				user.setAccount(account);
				users.put(user.getUserId(), user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at showusers Dao method : "+e);
				}
			}
		}

		return users;

	}



	public WalletUser deleteWalletUser(BigInteger userId) {

		return users.remove(userId);

	}



	public WalletUser searchUser(BigInteger userId) {

		String sql="select * from user where user_id=?";
		WalletUser user = new WalletUser();
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, userId.longValue());
			rs=ps.executeQuery();
			if(rs.next()) {
				user.setUserId(BigInteger.valueOf(rs.getLong(1)));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setPhoneNo(rs.getString("user_phone"));
				WalletAccount account = accDao.searchAccount(BigInteger.valueOf(rs.getLong("account_id")));
				user.setAccount(account);
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(" Error at addEmployee Dao method : "+e);
			}
		}
	}

return user;
	}
	@Override

	public Double getBalance(BigInteger userId) {

		WalletUser user=searchUser(userId);
		

		if(user!=null){

			return accDao.searchAccount(user.getAccount().getAccountNo()).getBalance();

		}

		return null;

	}



	@Override

	public Double addAmount(BigInteger userId,Double amount) {

		WalletUser user=searchUser(userId);

		if(user!=null){

			WalletAccount userAccount = user.getAccount();

			userAccount.setBalance(userAccount.getBalance()+amount);
			
			String sql_amount="update account set balance=? where account_id=?";
			
			try {
				ps=connection.prepareStatement(sql_amount);
				ps.setDouble(1, userAccount.getBalance());
				ps.setLong(2, userAccount.getAccountNo().longValue());
				ps.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				System.out.println("error in updating amount");
			}finally {
				if(ps!=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						System.out.println(" Error at addEmployee Dao method : "+e);
					}
				}
			}
			

			Transaction transaction = new Transaction("myself",LocalDateTime.now(),

					                                          amount,userAccount.getBalance());
			String sql="insert into transaction(trans_description,trans_amount,balance,trans_date,account_id)"
					+ " values(?,?,?,?,?)";
			
			try {
				ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, transaction.getDescription());
				ps.setDouble(2, transaction.getAmount());
				ps.setDouble(3, transaction.getBalance());
				ps.setDate(4, java.sql.Date.valueOf(transaction.getDateOfTransaction().toLocalDate()));
				ps.setLong(5, userAccount.getAccountNo().longValue());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(ps!=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						System.out.println(" Error at addEmployee Dao method : "+e);
					}
				}
			}
			

			

			return userAccount.getBalance();

		}

		return null;

	}



	@Override

	public Double transferAmount(BigInteger userId,String toAccount,Double amount) {

		WalletUser user=searchUser(userId);

		if(user!=null){

			WalletAccount userAccount = user.getAccount();

			if(userAccount.getBalance().compareTo(amount)>0){

				userAccount.setBalance(userAccount.getBalance()-amount);

			}

			else{

				return -1.0;

			}

			Transaction transaction = new Transaction(toAccount,LocalDateTime.now(),

					amount,userAccount.getBalance());
			String sql="insert into transaction(trans_description,trans_amount,balance,trans_date,account_id)"
					+ " values(?,?,?,?,?)";
			
			try {
				ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, transaction.getDescription());
				ps.setDouble(2, transaction.getAmount());
				ps.setDouble(3, transaction.getBalance());
				ps.setDate(4, java.sql.Date.valueOf(transaction.getDateOfTransaction().toLocalDate()));
				ps.setLong(5, userAccount.getAccountNo().longValue());
				ps.executeUpdate();
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(ps!=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						System.out.println(" Error at addEmployee Dao method : "+e);
					}
				}
			}


			return userAccount.getBalance();

		}

		return null;

	}



	@Override

	public List<Transaction> myTransactions(BigInteger userId, LocalDateTime date1, LocalDateTime date2) {

		WalletAccountDao accountDao = new WalletAccountDaoImpl();

		return accountDao.getTransactions(searchUser(userId).getAccount().getAccountNo(),date1,date2);

	}

}