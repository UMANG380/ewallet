package com.cg.onlinewallet.dao;

import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WalletUserDaoImpl implements WalletUserDao {
	
	private static Map<BigInteger,WalletUser> users = new HashMap<BigInteger, WalletUser>();
	private WalletAccountDao accDao= new WalletAccountDaoImpl();

	public WalletUser addWalletUser(WalletUser user) {
		
		users.put(user.getUserId(), user);
		accDao.addAccount(user.getAccount());
		return user;
	}

	public Map<BigInteger, WalletUser> showUsers() {
		return users;
	}

	public WalletUser deleteWalletUser(BigInteger userId) {
		return users.remove(userId);
	}

	public WalletUser searchUser(BigInteger userId) {
		return users.get(userId);
	}

	@Override
	public Double getBalance(BigInteger userId) {
		WalletUser user=searchUser(userId);
		if(user!=null){
			return user.getAccount().getBalance();
		}
		return null;
	}

	@Override
	public Double addAmount(BigInteger userId,Double amount) {
		WalletUser user=searchUser(userId);
		if(user!=null){
			WalletAccount userAccount = user.getAccount();
			userAccount.setBalance(userAccount.getBalance()+amount);
			Transaction transaction = new Transaction("myself",LocalDateTime.now(),
					                                          amount,userAccount.getBalance());
			userAccount.getTransactionList().add(transaction);
			return userAccount.getBalance();
		}
		return null;
	}

	@Override
	public Double transferAmount(BigInteger userId,String toAccount,Double amount) {
		WalletUser user=searchUser(userId);
		if(user!=null){
			WalletAccount userAccount = user.getAccount();
			if(userAccount.getBalance().compareTo(amount)>0){
				userAccount.setBalance(userAccount.getBalance()-amount);
			}
			else{
				return -1.0;
			}
			Transaction transaction = new Transaction(toAccount,LocalDateTime.now(),
					amount,userAccount.getBalance());
			userAccount.getTransactionList().add(transaction);
			return userAccount.getBalance();
		}
		return null;
	}

	@Override
	public List<Transaction> myTransactions(BigInteger userId, LocalDateTime date1, LocalDateTime date2) {
		WalletAccountDao accountDao = new WalletAccountDaoImpl();
		return accountDao.getTransactions(searchUser(userId).getAccount().getAccountNo(),date1,date2);
	}
}
