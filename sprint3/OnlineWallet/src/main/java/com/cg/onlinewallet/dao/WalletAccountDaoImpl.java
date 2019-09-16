
package com.cg.onlinewallet.dao;



import com.cg.onlinewallet.dto.Status;

import com.cg.onlinewallet.dto.Transaction;

import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.exception.MyException;
import com.cg.onlinewallet.util.DBUtil;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



//import static com.cg.onlinewallet.dto.Status.WaitingForApproval;



public class WalletAccountDaoImpl implements WalletAccountDao {

	

	private static Map<BigInteger,WalletAccount> accounts = new HashMap<BigInteger, WalletAccount>();
	private static Map<BigInteger,WalletUser> users = new HashMap<BigInteger, WalletUser>();

	//private WalletAccountDao accDao= new WalletAccountDaoImpl();
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




	public WalletAccount addAccount(WalletAccount account) {


        String sql="insert into account(account_status,balance) values(?,?)";
		try {
			ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "WatingForApproval");
			ps.setDouble(2, 0.0);
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if(rs.next()) {
				account.setAccountNo(BigInteger.valueOf(rs.getLong(1)));
			}
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

		return account;

	}



	public WalletAccount deleteAccount(WalletAccount acc) {

		

		if(accounts.containsKey(acc.getAccountNo())) {

			accounts.remove(acc.getAccountNo());

			return acc;

		}

		return null;

	}



	public WalletAccount searchAccount(BigInteger accountNo) {
		String sql="select * from account where account_id=?";
		WalletAccount account=new WalletAccount();
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, accountNo.longValue());
			rs=ps.executeQuery();
			if(rs==null) {
				return null;
			}
			if(rs.next()) {
			account.setAccountNo(BigInteger.valueOf(rs.getLong("account_id")));
			String status=rs.getString("account_status");
			if(status.equals("WatingForApproval")) {
				account.setAccountStatus(Status.WatingForApproval);
			}
			else if(status.equals("Approved")){
				account.setAccountStatus(Status.Approved);
			}
			else {
				account.setAccountStatus(Status.Rejected);
			}
			account.setBalance(rs.getDouble("balance"));
			}
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

        return account;
	}

	

	public List<Transaction> getTransactions(BigInteger accountNo,LocalDateTime time1,LocalDateTime time2){

		

		WalletAccount account = searchAccount(accountNo);
		String sql="select * from transaction where account_id=?";

		List<Transaction> transactions=new ArrayList<Transaction>();

		List<Transaction> ret = new ArrayList<Transaction>();
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, accountNo.longValue());
			rs=ps.executeQuery();
			while(rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setDescription(rs.getString("trans_description"));
				transaction.setTransactionId(BigInteger.valueOf(rs.getLong("trans_id")));
				transaction.setAmount(rs.getDouble("trans_amount"));
				transaction.setBalance(rs.getDouble("balance"));
				java.sql.Date date=rs.getDate("trans_date");
				LocalDate dateTime=date.toLocalDate();
				LocalDateTime ldt=dateTime.atTime(0, 0);
				transaction.setDateOfTransaction(ldt);
				transactions.add(transaction);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		account.setTransactionList(transactions);

		if(account!=null) {

			transactions = account.getTransactionList();

			if(transactions!=null) {

				for(int i=0;i<transactions.size();i++) {



					if(transactions.get(i).getDateOfTransaction().compareTo(time1)>=0&&

							transactions.get(i).getDateOfTransaction().compareTo(time2)<=0) {

						ret.add(transactions.get(i));

					}

				}

				return ret;

			}

		}

		return null;

		

	}

	

//	public Transaction addTransaction(BigInteger accountNo,Transaction transaction) {
//
//		WalletAccount account = searchAccount(accountNo);
//
//		account.setTransactionList(transactionList);
//
//		return transaction;
//
//	}



	@Override

	public List<WalletAccount> accountsToBeApproved() {

       String sql="select * from account where account_status=?";
       List<WalletAccount> accounts=new ArrayList<WalletAccount>();
       try {
		ps=connection.prepareStatement(sql);
		ps.setString(1, "WatingForApproval");
		rs=ps.executeQuery();
		while(rs.next()) {
			WalletAccount account = new WalletAccount();
			account.setAccountNo(BigInteger.valueOf(rs.getLong(1)));
			account.setAccountStatus(Status.WatingForApproval);
			account.setBalance(rs.getDouble("balance"));
			accounts.add(account);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return accounts;

	}



	@Override

	public WalletAccount approveAccount(BigInteger accountNo) {
		String sql="update account set account_status=? where account_id=?";
		WalletAccount account = new WalletAccount();
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, "Approved");
			ps.setLong(2, accountNo.longValue());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error in approve account method");
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at addEmployee Dao method : "+e);
				}
			}
		}
		
		return account;		
	}

}