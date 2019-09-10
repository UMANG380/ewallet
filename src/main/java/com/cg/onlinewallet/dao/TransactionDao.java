package com.cg.onlinewallet.dao;

import java.math.*;
import java.util.HashMap;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.Transaction;

public interface TransactionDao {
	public Transaction addTransaction(Transaction t);
	public HashMap<BigInteger, Transaction> showTransaction(BigInteger tid);

}
