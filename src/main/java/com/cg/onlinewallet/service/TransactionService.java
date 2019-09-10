package com.cg.onlinewallet.service;

import java.math.BigInteger;

import com.cg.onlinewallet.dto.Transaction;

public interface TransactionService {
	public Transaction addTransaction(Transaction t);
	public Transaction showTransaction(BigInteger tid);
}
