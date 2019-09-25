package com.cg.walletaccounttest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.exception.MyException;
import com.cg.onlinewallet.service.WalletUserService;
import com.cg.onlinewallet.service.WalletUserServiceImpl;


public class OnlineWalletTest {
	
	private WalletUserService userService;
	private WalletUser walletUser;
	
	@BeforeAll
	public static void set() {
		
	}
	
	@BeforeEach
	public void setObject() {
		 userService= new WalletUserServiceImpl();
		 walletUser = new WalletUser();
	}
	
	@Test
	public void checkAddAmount(){
		try {
			walletUser = userService.getUser((1));
		} catch (MyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Double value=userService.getBalance(1);
			userService.addAmount(1, 10.00);
			Double updatedValue=userService.getBalance(1);
			assertEquals(value,updatedValue-10);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			
			
		}
	}
	
	@Test
	public void checkTransferAmount() {
		try {
			Double value=userService.getBalance(1);
		    userService.transferAmount(1,"1234567890", 100.00);
			assertEquals(value,userService.getBalance(1)+100.0);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	@Test
	public void checkTransferToAccount() {
		try {
			Double value=userService.getBalance(1);
		    userService.transferAmount(1,835238, 100.00);
			assertEquals(value,userService.getBalance(1)+100.0);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	
	
	
	@AfterAll
	public static void finish() {
		System.out.println("Program has been executed");
	}
	
	@AfterEach
	public  void afterTest() {
	
	}
}
