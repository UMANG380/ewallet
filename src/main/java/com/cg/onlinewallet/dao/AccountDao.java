package com.cg.onlinewallet.dao;

import java.math.*;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.User;

public interface AccountDao {
	public User addBankAccount(Account acc);
	public Boolean deleteBankAccount(Account acc);
	public Account showBankAccount(BigInteger id, String password);

}
