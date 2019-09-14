package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletUser;

public interface WalletUserDao {
	
	public WalletUser addWalletUser(WalletUser user);
	public Map<BigInteger, WalletUser> showUsers();
	public WalletUser deleteWalletUser(BigInteger userId);
	public WalletUser searchUser(BigInteger userId);
	
}
