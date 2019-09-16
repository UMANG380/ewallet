package com.cg.onlinewallet.ui;



import com.cg.onlinewallet.dto.Transaction;

import com.cg.onlinewallet.dto.WalletAccount;

import com.cg.onlinewallet.dto.WalletUser;

import com.cg.onlinewallet.exception.MyException;

import com.cg.onlinewallet.service.WalletUserService;

import com.cg.onlinewallet.service.WalletUserServiceImpl;



import java.math.BigInteger;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;

import java.util.List;

import java.util.Scanner;



public class MyApplication {



	public static void main(String[] args) {

		Scanner scanner= new Scanner(System.in);

	    WalletUserService userService = new WalletUserServiceImpl();



		WalletUser user1 = new WalletUser("Venkatesh","abcd","9951800546",new WalletAccount());

		WalletUser user2= new WalletUser("sutya","abcd","9003432137",new WalletAccount());



		try {

			userService.addWalletUser(user1);

			userService.addWalletUser(user2);

		} catch (MyException e) {

			System.out.println(e.getMessage());

		}



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

			     				continue;

			     			}

			        		switch(choice) {

			        		case 1:

								    List<WalletAccount> accounts = userService.accountsToBeApproved();

								    if(accounts.size()==0){

										System.out.println("all are approved");

									}

								    else{

								    	for(int i=0;i<accounts.size();i++){

											System.out.println(accounts.get(i));

										}

									}

			        			    break;

			        		case 2:

								    System.out.println("Enter the account no");

								    String value = scanner.nextLine();

								    Long lValue=0L;

								    try{

										lValue=Long.parseLong(value);

									}

								    catch(NumberFormatException e){

										System.out.println("Enter a valid no");

										continue;

									}

								try {

									userService.approveAccount(BigInteger.valueOf(lValue));

								} catch (MyException e) {

									System.out.println(e.getMessage());



								}

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

			         break;

			case 2:

				 System.out.println("Enter the userId");

		         username=scanner.nextLine();

		         Long id=0L;

		         try{

		         	id=Long.parseLong(username);

				 }

		         catch(NumberFormatException e){

					 System.out.println("Enter a valid id");

					 continue;

				 }

		         System.out.println("Enter the password");

		         password=scanner.nextLine();

		         if(userService.validateLoginCredentials(BigInteger.valueOf(id),password)) {

		        	 WalletUser user = userService.getUser(BigInteger.valueOf(id));

					 System.out.println("Welcome "+user.getUserName());



		        	 while(choice!=5) {



		        		 System.out.println("1. To check account balance");

		        		 System.out.println("2. To tranfer funds");

		        		 System.out.println("3. Add amount to the wallet" );

		        		 System.out.println("4. Ministatement");

		        		 System.out.println("5. Log out");

						 try {

							 choice=Integer.parseInt(scanner.nextLine());

						 }

						 catch(NumberFormatException e){

							 System.out.println("Enter a valid number");

							 continue;

						 }

		        		 if(user.getAccount().getAccountStatus().toString().equals("Approved")) {

							 switch (choice) {

								 case 1:

									 System.out.println(user.getAccount().getBalance());

									 break;

								 case 2:

									 System.out.println("Enter the account no to be transferred");

									 String str= scanner.nextLine();

									 System.out.println("Enter the amount to be transferred");

									 String amountToTransfer=scanner.nextLine();

									 Double amount = 0.0;

									 try{

									 	amount=Double.parseDouble(amountToTransfer);

										 System.out.println("updated balance :"+

										 		userService.transferAmount(user.getUserId(),str,amount));

									 }

									 catch(NumberFormatException e){

										 System.out.println("enter a valid amount");

									 } catch (MyException e) {

										 System.out.println("balance insufficient");

									 }

									 break;

								 case 3:

									 System.out.println("Enter the amount to add");

									 try{

										 amount = Double.parseDouble(scanner.nextLine());

										 userService.addAmount(user.getUserId(),amount);

									 }

									 catch(NumberFormatException e){

										 System.out.println("Enter a valid amount");

									 } catch (MyException e) {

										 System.out.println(e.getMessage());

									 }

									 break;

								 case 4:

									 try {

										 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

									 	 LocalDateTime date=LocalDateTime.of(2019,02,10,2,3);

										 List<Transaction> transactions = userService.myTransactions(user.getUserId(),date

												,LocalDateTime.now());

										 for(int i=0;i<transactions.size();i++){

											 System.out.println("Date : "+transactions.get(i).getDateOfTransaction()+

													 " Amount : "+transactions.get(i).getAmount()+

													 " tranferred to"

											 		+" "+transactions.get(i).getDescription()+" Balance :" +transactions.get(i).getBalance());

										 }

									 } catch (MyException e) {

										 System.out.println(e.getMessage());

									 } catch (Exception e) {

										 System.out.println("enter a valid date");

									 }

									 break;

								 case 5:

									 System.out.println("logging you out......");

									 choice = 5;

									 break;

								 default:

									 System.out.println("Enter a valid choice");

							 }

						 }

		        		 else{

							 System.out.println("Your account is waiting for the approval please" +

									 "contact admin for further issues");

							 choice=5;

						 }

		        		 

		        	 }

		         }

		         else{

					 System.out.println("Invalid username and password");

		         }

				  

				  break;

			case 3:

				System.out.println("Welcome user register your account here");

				boolean validInput=true;

				while(validInput){

					System.out.println("Enter the userName");

					String userName=scanner.nextLine();

					System.out.println("Set your password");

					String password1=scanner.nextLine();

					System.out.println("Confirm your password");

					String password2=scanner.nextLine();

					if(password1.equals(password2)&&password1.length()>=8){

						System.out.println("Enter the phone number");

						String number=scanner.nextLine();

						WalletUser user = new WalletUser(userName,password1,number,

								new WalletAccount());

						try {

							user=userService.addWalletUser(user);

							System.out.println("Your user id is "+user.getUserId()+" " +

									"remember this for long time");

							System.out.println("Your account number is "+ user.getAccount().getAccountNo());

							validInput=false;

						} catch (MyException e) {

							System.out.println(e.getMessage());

							validInput=false;

						}

					}

					else{

						if(!password1.equals(password2)){

							System.out.println("passwords are not matching");

						}

						else{

							System.out.println("password should be 8 digits");

						}

					}



				}





				

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