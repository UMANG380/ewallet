package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cg.onlinewallet.dto.WalletUser;

public class WalletUserDaoImpl implements WalletUserDao {
	
	private static Map<BigInteger,WalletUser> users = new HashMap<BigInteger, WalletUser>();

	public WalletUser addWalletUser(WalletUser user) {
		
		users.put(user.getUserId(), user);
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
	

}
