package com.cg.onlinewallet.dao;

import com.cg.onlinewallet.dto.Transaction;

public interface TransactionDao {
	
	public Transaction addTransaction(String description);
}
