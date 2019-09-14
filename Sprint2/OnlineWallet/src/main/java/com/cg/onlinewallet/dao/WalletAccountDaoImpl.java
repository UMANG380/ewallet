package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletAccount;

public class WalletAccountDaoImpl implements WalletAccountDao {
	
	private static Map<BigInteger,WalletAccount> accounts = new HashMap<BigInteger, WalletAccount>();

	public WalletAccount addAccount(WalletAccount account) {
		
		accounts.put(account.getAccountNo(), account);
		return account;
	}

	public WalletAccount deleteAccount(WalletAccount acc) {
		
		if(accounts.containsKey(acc.getAccountNo())) {
			accounts.remove(acc.getAccountNo());
			return acc;
		}
		return null;
	}

	public WalletAccount searchAccount(BigInteger accountNo) {
		
		return accounts.get(accountNo);
	}
	
	public List<Transaction> getTransactions(BigInteger accountNo,LocalDateTime time1,LocalDateTime time2){
		
		WalletAccount account = accounts.get(accountNo);
		List<Transaction> transactions = new ArrayList<Transaction>();
		List<Transaction> ret = new ArrayList<Transaction>();
		if(account!=null) {
			transactions = account.getTransactionList();
			if(transactions!=null) {
				for(int i=0;i<transactions.size();i++) {
					if(transactions.get(i).getDateOfTransaction().compareTo(time1)>=0&&
							transactions.get(i).getDateOfTransaction().compareTo(time2)<=0) {
						ret.add(transactions.get(i));
					}
				}
				return ret;
			}
		}
		return null;
		
	}
	
	public Transaction addTransaction(BigInteger accountNo,Transaction transaction) {
		WalletAccount account = accounts.get(accountNo);
		account.getTransactionList().add(transaction);
		return transaction;
	}

}
