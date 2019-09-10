package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.User;

public class AccountDaoImpl implements AccountDao {
	HashMap<BigInteger, Account> hm2 = new HashMap<BigInteger, Account>();
	
	public Account addBankAccount(Account acc) {
		// TODO Auto-generated method stub
		hm2.put(acc.getAccountNo(), acc);
		return acc;
		
	}
	

	public Boolean deleteBankAccount(Account acc) {
		// TODO Auto-generated method stub
		if(hm2.remove(acc)!=null)
			return true;
		else
			return false;
		
	}

	public HashMap<BigInteger,Account> showBankAccount() {
		// TODO Auto-generated method stub
		return hm2;
	}
	/*public Transaction addTransaction(Transaction t) {
		// TODO Auto-generated method stub
		transactionList1.add(t);
		return null;
	}*/

}
