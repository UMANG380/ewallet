package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.util.HashMap;

import com.cg.onlinewallet.dto.Account;
import com.cg.onlinewallet.dto.User;

public interface UserDao {
	
	public User<Account> addUser(User<Account> u);
	public HashMap<BigInteger, User<Account>> showUser();
	public boolean deleteUser(BigInteger id);
	public User<Account> searchUser(BigInteger id);
	

}
