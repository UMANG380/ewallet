package com.cg.onlinewallet.service;

import java.math.BigInteger;
import java.util.HashMap;

import com.cg.onlinewallet.dto.User;

public interface UserService {
	
	public User addUser(User u);
	public HashMap<BigInteger, User> showUser();
	public boolean deleteUser(BigInteger id);
	public User searchUser(BigInteger id);

}
