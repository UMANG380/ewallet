package com.cg.onlinewallet.service;

import java.math.BigInteger;
import java.util.HashMap;

import com.cg.onlinewallet.dao.TransactionDao;
import com.cg.onlinewallet.dao.TransactionDaoImpl;
import com.cg.onlinewallet.dto.Transaction;

public class TransactionServiceImpl implements TransactionService {
	
	TransactionDao dao = new TransactionDaoImpl();

	public Transaction addTransaction(Transaction t) {
		// TODO Auto-generated method stub
		return dao.addTransaction(t);
	}

	public HashMap<BigInteger, Transaction> showTransaction(BigInteger tid) {
		// TODO Auto-generated method stub
		return dao.showTransaction(tid);
	}

}
