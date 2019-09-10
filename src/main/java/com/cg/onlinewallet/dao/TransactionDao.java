package com.cg.onlinewallet.dao;

import java.math.*;

import com.cg.onlinewallet.dto.Transaction;

public interface TransactionDao {
	public Transaction addTransaction(Transaction t);
	public Transaction showTransaction(BigInteger tid);

}
