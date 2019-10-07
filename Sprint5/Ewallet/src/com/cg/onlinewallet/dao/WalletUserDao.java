package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.cg.onlinewallet.dto.TransactionHistory;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;

/**
 * @author Admin
 *
 */
public interface WalletUserDao {
	
	public WalletUser addUser(WalletUser user);
	public Double addAmount(Integer userId,Double amount);
	public Double transferAmount(Integer fromUserId,String phoneNumber,Double amount);
	public Double transferAmount(Integer fromUserId,Integer accountNo,Double amount);
	public Double checkBalance(Integer userId);
	public List<WalletUser>getAllUsers();
	public WalletUser getUser(Integer userId);
	/**
	 * @param accountId
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<TransactionHistory> getTransactions(Integer accountId
			,LocalDateTime fromDate,LocalDateTime toDate);
	public List<WalletAccount> getAccountsToApprove();
	public WalletAccount approveAccount(Integer accountId);
	public WalletAccount getAccount(Integer accountNo);
}
 