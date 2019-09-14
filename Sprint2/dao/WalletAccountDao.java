package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.Map;

import com.cg.onlinewallet.dto.WalletAccount;

public interface WalletAccountDao {
	public WalletAccount addAccount(WalletAccount account);
	public Boolean deleteAccount(WalletAccount acc);
	public Map<BigInteger,WalletAccount> showAccount();

}
