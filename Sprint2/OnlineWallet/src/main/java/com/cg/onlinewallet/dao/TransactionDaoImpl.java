package com.cg.onlinewallet.dao;

import com.cg.onlinewallet.dto.Transaction;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class TransactionDaoImpl implements TransactionDao {
	Map<BigInteger, Transaction> transactionDetails = new HashMap<BigInteger, Transaction>();

	public Transaction addTransaction(Transaction transaction) {

		transactionDetails.put(transaction.getTransactionId(), transaction);
		return transaction;
	}

	public Map<BigInteger, Transaction> showTransaction(BigInteger transactionId) {

		return null;
	}

}
