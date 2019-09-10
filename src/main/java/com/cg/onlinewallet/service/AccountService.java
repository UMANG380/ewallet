package com.cg.onlinewallet.service;

import java.math.BigInteger;
import java.util.HashMap;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.User;

public interface AccountService {
	public Account addBankAccount(Account acc);
	public Boolean deleteBankAccount(Account acc);
	public HashMap<BigInteger,Account> showBankAccount();
	public Transaction addTransaction(Transaction t);

}
