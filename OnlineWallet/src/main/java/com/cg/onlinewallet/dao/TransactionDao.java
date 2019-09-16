package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.Map;

import com.cg.onlinewallet.dto.Transaction;

public interface TransactionDao {
	public Transaction addTransaction(Transaction transaction);
	public Map<BigInteger, Transaction> showTransaction(BigInteger transactionId);

}
