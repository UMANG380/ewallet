package com.cg.onlinewallet.dao;

import java.math.*;
import java.util.HashMap;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.User;

public interface AccountDao {
	public Account addBankAccount(Account acc);
	public Boolean deleteBankAccount(Account acc);
	public HashMap<BigInteger,Account> showBankAccount();


}
