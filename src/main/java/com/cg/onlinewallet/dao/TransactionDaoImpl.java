package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.HashMap;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.Transaction;

public class TransactionDaoImpl implements TransactionDao {
	HashMap<BigInteger, Transaction> hm3 = new HashMap<BigInteger, Transaction>();

	public Transaction addTransaction(Transaction t) {
		// TODO Auto-generated method stub
		hm3.put(t.getTxId(), t);
		return t;
		
		
	}

	public HashMap<BigInteger,Transaction> showTransaction(BigInteger tid) {
		// TODO Auto-generated method stub
		return hm3;
	}

}
