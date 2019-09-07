package com.cg.onlinewallet.ui;

import java.math.BigInteger;
import java.util.HashMap;

import java.util.Scanner;

import com.cg.onlinewallet.dto.User;
import com.cg.onlinewallet.service.UserService;
import com.cg.onlinewallet.service.UserServiceImpl;



public class MyApplication {
	public static void main(String[] args)  {

	
	Scanner sc = new Scanner(System.in);
	
	UserService service = new UserServiceImpl();
	
	User userOne;
	
	HashMap<BigInteger, User> hm = null;
	
	int choice;
		
	
	do {
		System.out.println("Enter choice");
		print();
		choice = sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("Enter id");
			BigInteger id = sc.nextBigInteger();
			System.out.println("Enter Name");
			String name = sc.next();
			System.out.println("Enter Password");
			String password = sc.next();
			System.out.println("Enter Address");
			String address = sc.next();
			System.out.println("Enter Phone Number");
			BigInteger phoneNo = sc.nextBigInteger();
			userOne = new User(id,name,password,address,phoneNo);
			service.addUser(userOne);
			hm = service.showUser();
			break;
		
		case 2:
			System.out.println("Enter the User id you want to delete");
			BigInteger uid = sc.nextBigInteger();
			 boolean status = service.deleteUser(uid);
			System.out.println("The employee detalis has been deleted");
			break;
		case 3:
			System.out.println("Enter the user id you want to search");
			id = sc.nextBigInteger();
		    User u =  service.searchUser(id);
			System.out.println(u);
			break;
			
		}
	} while(choice!=4);
	
	}

    public static void print() {
    	System.out.println("1:To add user");
    	System.out.println("2:Delete User");
    	System.out.println("3:Search User");
    	System.out.println("4: Exit");
    	}
	
}
