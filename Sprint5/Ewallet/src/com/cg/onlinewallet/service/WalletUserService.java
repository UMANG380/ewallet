package com.cg.onlinewallet.service;

	import com.cg.onlinewallet.dto.TransactionHistory;
	import com.cg.onlinewallet.dto.WalletAccount;
	import com.cg.onlinewallet.dto.WalletUser;
	import com.cg.onlinewallet.exception.MyException;
	import java.math.BigInteger;
	import java.time.LocalDateTime;
	import java.util.List;
	
	public interface WalletUserService {



	    public Double getBalance(Integer userId) throws MyException;

	    public Double addAmount(Integer userId,Double amount) throws MyException;

	    public Double transferAmount(Integer userId,String toAccount,Double amount) throws MyException;

	    public List<TransactionHistory> myTransactions(Integer userId, LocalDateTime date1, LocalDateTime date2) throws MyException;

	    public List<WalletAccount> accountsToBeApproved();

	    public WalletAccount approveAccount(Integer accountNo) throws MyException;

	    public WalletUser addWalletUser(WalletUser user) throws MyException;

	    public boolean validateLoginCredentials(Integer userId,String password);

	    public WalletUser getUser(Integer userId) throws MyException;
	    
	    public Double transferAmount(Integer fromUserId,Integer toUserId,Double amount) throws MyException;
	    
	    public WalletAccount getAccount(Integer accountNo);
	}


