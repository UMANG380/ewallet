package com.cg.onlinewallet.service;



import com.cg.onlinewallet.dao.WalletUserDao;

import com.cg.onlinewallet.dao.WalletUserDaoImpl;

import com.cg.onlinewallet.dto.WalletUser;

import com.cg.onlinewallet.exception.MyException;



import java.math.BigInteger;

import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.List;

import java.util.Map;



class Validate {

	

	static void  validateId(BigInteger userId) throws MyException {

		String str = ""+userId;

		if(!str.matches("\\d+")) {

			throw new MyException("userId consists only digits");

		}

	}

	

	static void validateDate(LocalDateTime date) throws MyException {

		if(LocalDateTime.now().isBefore(date)){

			throw new MyException("Invalid date");

		}

	}



	static void validateDuplicate(WalletUser user) throws MyException {

        WalletUserDao userDao = new WalletUserDaoImpl();

        List<Map.Entry<BigInteger,WalletUser>> users = new ArrayList<>(userDao.showUsers().entrySet());

        for(int i=0;i<users.size();i++){

            if(users.get(i).getValue().equals(user)){

                throw new MyException("Account is already present please try to login");

            }

        }

	}

	static void validatePhoneNumber(String number) throws MyException {

		if(!number.matches("\\d{10}")){

			throw new MyException("Phone number must be 10 digits");

		}

	}



}