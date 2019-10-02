package com.cg.onlinewallet.dao;

import com.cg.onlinewallet.dto.TransactionHistory;

public interface TransactionDao {
	
	public TransactionHistory addTransaction(String description);
}
