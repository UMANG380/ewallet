package com.cg.onlinewallet.ui;

import java.util.Scanner;

public class MyApplication {

	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
//	    WalletUserService userService = new WalletUserServiceImpl();
		
		System.out.println("Welcome to our Online Wallet System");
		int option=0;
		String username=null;
		String password=null;
		while(option!=4) {
			int choice=0;
			System.out.println("Choose the type of user");
			System.out.println("1. Admin");
			System.out.println("2. Registered User");
			System.out.println("3. Unregistered User");
			System.out.println("4. Quit");
			
			try {
				option=Integer.parseInt(scanner.nextLine());
			}
			catch(NumberFormatException e){
				System.out.println("Enter a valid number");
				continue;
			}
			
			switch(option) {
			
			case 1:  System.out.println("Enter the username");
			         username=scanner.nextLine();
			         System.out.println("Enter the password");
			         password=scanner.nextLine();
			         
			         if(username.equals("venkatesh") /*userService.validate("adm"+username,password)*/) {
			        	 
			        	 System.out.println("Logged in successfully");
			        	 while(choice!=3) {
			        		 System.out.println("1. view accounts to be approved");
				        	 System.out.println("2. approve accounts");
				        	 System.out.println("3. log out");
			        		 try {
			     				choice=Integer.parseInt(scanner.nextLine());
			     			}
			     			catch(NumberFormatException e){
			     				System.out.println("Enter a valid number");
			     			}
			        		switch(choice) {
			        		case 1:
			        			    break;
			        		case 2:
			        			    break;
			        		case 3:
			        			    System.out.println("Logging you out......");
			        			    choice=3;
			        			    break;
			        		default:
			        			   System.out.println("Enter a valid option");
			        			    
			        		}
			        	 }
			         }
			         else {
			        	 System.out.println("Invalid username and password");
			         }
			case 2:
				 System.out.println("Enter the username");
		         username=scanner.nextLine();
		         System.out.println("Enter the password");
		         password=scanner.nextLine();
		         if(username.equals("venkatesh") /*userService.validate("cus"+username,password)*/) {
		        	 System.out.println("Welcome "+username);
		        	 while(choice!=5) {
		        		 System.out.println("1. To check account balance");
		        		 System.out.println("2. To tranfer funds");
		        		 System.out.println("3. Add amount to the wallet" );
		        		 System.out.println("4. ministatement");
		        		 System.out.println("5. Log out");
		        		 
		        		 switch(choice) {
		        		 case 1:
		        			 break;
		        		 case 2:
		        			 break;
		        		 case 3:
		        			 break;
		        		 case 4:
		        			 break;
		        		 case 5:
		        			 System.out.println("logging you out......");
		        			 break;
		        		 default:
		        			 System.out.println("Enter a valid choice");
		        		 }
		        		 
		        	 }
		         }
				  
				  break;
			case 3:
				System.out.println("Welcome user");
				System.out.println("Register account");
				//Entering details and checking duplication
				//userService.validateDuplicateUser(User);
				
				break;
			case 4:
				option=4;
				System.out.println("Thankyou");
				break;
			default:
				System.out.println("Enter a valid option");
		   }
		}
		

	}

}
