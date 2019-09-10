package com.cg.onlinewallet.service;

import java.math.BigInteger;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.User;

public interface AccountService {
	public User addBankAccount(Account acc);
	public Boolean deleteBankAccount(Account acc);
	public Account showBankAccount(BigInteger id, String password);

}
