package com.cg.onlinewallet.service;

import java.math.BigInteger;

import com.cg.onlinewallet.exception.MyException;

class Validate {
	
	static void  validateId(BigInteger userId) throws MyException {
		String str = ""+userId;
		if(!str.matches("\\d+")) {
			throw new MyException("userId consists only didgits");
		}
	}
	
	static void validateUserName(String userName) {
		
	}

}
