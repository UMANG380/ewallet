package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.cg.onlinewallet.dto.User;

public class UserDaoImpl implements UserDao {
	
	HashMap<BigInteger, User> hm = new HashMap<BigInteger, User>();

	public User addUser(User u) {
		hm.put(u.getUserId(), u);
		return u;
	}

	public  HashMap<BigInteger, User> showUser() {
		return hm;
	}

	public boolean deleteUser(BigInteger id) {
		if(hm.remove(id)!=null)
			return true;
		else
			return false;
	}

	public User searchUser(BigInteger id) {
		return hm.get(id);
	}

	
	
	

}
