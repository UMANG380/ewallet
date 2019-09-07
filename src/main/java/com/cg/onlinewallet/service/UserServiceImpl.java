package com.cg.onlinewallet.service;

import java.math.BigInteger;
import java.util.HashMap;

import com.cg.onlinewallet.dao.UserDao;
import com.cg.onlinewallet.dao.UserDaoImpl;
import com.cg.onlinewallet.dto.User;

public class UserServiceImpl implements UserService {
	
	UserDao dao = new UserDaoImpl();

	public User addUser(User u) {
		return dao.addUser(u);
	}

	public HashMap<BigInteger, User> showUser() {
		return dao.showUser();
	}

	public boolean deleteUser(BigInteger id) {
		return dao.deleteUser(id);
	}

	public User searchUser(BigInteger id) {
		return dao.searchUser(id);
		
	}

	

}
