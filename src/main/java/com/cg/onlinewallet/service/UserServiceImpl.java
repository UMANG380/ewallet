package com.cg.onlinewallet.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.onlinewallet.dao.UserDao;
import com.cg.onlinewallet.dao.UserDaoImpl;
import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.User;

public class UserServiceImpl implements UserService {
	
	UserDao dao = new UserDaoImpl();

	public User<Account> addUser(User<Account> u) {
		return dao.addUser(u);
	}

	public HashMap<BigInteger, User<Account>> showUser() {
		Map<BigInteger, User<Account>> map = dao.showUser();
		List<User<Account>> list = newArrayList<User<Account>();
		
		
		
		return dao.showUser();
	}

	public boolean deleteUser(BigInteger id) {
		return dao.deleteUser(id);
	}

	public User<Account> searchUser(BigInteger id) {
		List<User<Account>> list = showUser();
		return dao.searchUser(id);
		
	}

	

}
