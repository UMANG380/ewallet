package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cg.onlinewallet.dto.Transaction;

public class TransactionDaoImpl implements TransactionDao {
	Map<BigInteger, Transaction> transactionDetails = new HashMap<BigInteger, Transaction>();

	public Transaction addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionDetails.put(transaction.getTransactionId(), transaction);
		return transaction;
	}

	public Map<BigInteger, Transaction> showTransaction(BigInteger transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
