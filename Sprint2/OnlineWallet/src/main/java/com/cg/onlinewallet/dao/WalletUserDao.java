package com.cg.onlinewallet.dao;

import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletUser;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface WalletUserDao {
	
	public WalletUser addWalletUser(WalletUser user);
	public Map<BigInteger, WalletUser> showUsers();
	public WalletUser deleteWalletUser(BigInteger userId);
	public WalletUser searchUser(BigInteger userId);
	public Double getBalance(BigInteger userId);
	public Double addAmount(BigInteger userId,Double amount);
	public Double transferAmount(BigInteger userId,String toAccount,Double amount);
	public List<Transaction> myTransactions(BigInteger userId, LocalDateTime date1,LocalDateTime date2);

	
}
