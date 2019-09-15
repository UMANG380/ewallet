package com.cg.onlinewallet.dao;

import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletAccount;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

//import java.util.Map;

public interface WalletAccountDao {
	
	
    WalletAccount addAccount(WalletAccount account);
	public WalletAccount deleteAccount(WalletAccount acc);
	public WalletAccount searchAccount(BigInteger accountNo);
	public List<Transaction> getTransactions(BigInteger accountNo,LocalDateTime time1,LocalDateTime time2);
	public Transaction addTransaction(BigInteger accountNo,Transaction transaction);
	public List<WalletAccount> accountsToBeApproved();
	public WalletAccount approveAccount(BigInteger accountNo);

}
