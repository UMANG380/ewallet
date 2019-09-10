package com.cg.onlinewallet.service;

import java.math.BigInteger;
import java.util.HashMap;

import com.cg.onlinewallet.dto.Transaction;

public interface TransactionService {
	public Transaction addTransaction(Transaction t);
	public HashMap<BigInteger, Transaction> showTransaction(BigInteger tid);
}
