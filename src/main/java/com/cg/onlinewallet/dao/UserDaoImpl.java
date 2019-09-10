package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.User;

public class UserDaoImpl implements UserDao {
	
	HashMap<BigInteger, User<Account>> hm = new HashMap<BigInteger, User<Account>>();

	public User<Account> addUser(User<Account> u) {
		hm.put(u.getUserId(), u);
		return u;
	}

	public  HashMap<BigInteger, User<Account>> showUser() {
		return hm;
	}

	public boolean deleteUser(BigInteger id) {
		if(hm.remove(id)!=null)
			return true;
		else
			return false;
	}

	public User<Account> searchUser(BigInteger id) {
		return hm.get(id);
	}

	
	
	

}
