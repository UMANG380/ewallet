package com.cg.onlinewallet.dao;

import com.cg.onlinewallet.exception.MyException;
import com.cg.onlinewallet.util.DBUtil;
import com.cg.onlinewallet.dto.Status;
import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletAccount;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

		accounts.put(account.getAccountNo(), account);
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
		
		return accounts.get(accountNo);
	}
	
	public List<Transaction> getTransactions(BigInteger accountNo,LocalDateTime time1,LocalDateTime time2){
		
		WalletAccount account = accounts.get(accountNo);
		List<Transaction> transactions;
		List<Transaction> ret = new ArrayList<Transaction>();
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
	
	public Transaction addTransaction(BigInteger accountNo,Transaction transaction) {
		WalletAccount account = accounts.get(accountNo);
		account.getTransactionList().add(transaction);
		return transaction;
	}

	@Override
	public List<WalletAccount> accountsToBeApproved() {

		List<Map.Entry<BigInteger,WalletAccount>> ret = new ArrayList<>(accounts.entrySet());
		List<WalletAccount> walletAccounts = new ArrayList<>();
		for(int i=0;i<ret.size();i++){
			if(ret.get(i).getValue().getAccountStatus().toString().equals("WatingForApproval")){
                System.out.println(ret.get(i).getValue().getAccountNo());
				walletAccounts.add(ret.get(i).getValue());
			}
		}
		return walletAccounts;
	}

	@Override
	public WalletAccount approveAccount(BigInteger accountNo) {
	    if(searchAccount(accountNo)==null){
	        return null;
        }
		searchAccount(accountNo).setAccountStatus(Status.Approved);
		return searchAccount(accountNo);
	}
}
