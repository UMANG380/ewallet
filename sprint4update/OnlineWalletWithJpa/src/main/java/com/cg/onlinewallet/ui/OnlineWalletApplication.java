//package com.cg.onlinewallet.ui;
//
//import com.cg.onlinewallet.dao.WalletUserDao;
//import com.cg.onlinewallet.dao.WalletUserDaoImpl;
//
//public class OnlineWalletApplication {
//	
//	public static void main(String[] args) {
//		
////		WalletUser user = new WalletUser();
////		user.setUserName("Utkarsh");
////		user.setPhoneNo("9767123546");
////		user.setUserPassword("12345");
////		
//		WalletUserDao dao =new WalletUserDaoImpl();
////		dao.addUser(user);
//		
////		user=dao.addAmount(1, 200.0);
////		dao.addAmount(1, 10.0);
////		
////		for(int i=0;i<dao.getTransactions(1).size();i++) {
////			System.out.println(dao.getTransactions(1).get(i).getDescription()
////					+" "+dao.getTransactions(1).get(i).getAmount()+" "+dao.getTransactions(1).get(i).getBalance());
////		}
//		
//	//	dao.transferAmount(1,"9767123546", 120.0);
//		
//	}
//
//}
package com.cg.onlinewallet.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.cg.onlinewallet.dto.TransactionHistory;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.exception.MyException;
import com.cg.onlinewallet.service.WalletUserService;
import com.cg.onlinewallet.service.WalletUserServiceImpl;

public class OnlineWalletApplication {

	static WalletUserService userService = new WalletUserServiceImpl();

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to our Online Wallet System");

		int option = 0;

		String username = null;

		String password = null;

		while (option != 4) {

			int choice = 0;

			System.out.println("Choose the type of user");

			System.out.println("1. Admin");

			System.out.println("2. Registered User");

			System.out.println("3. Unregistered User");

			System.out.println("4. Quit");

			try {

				option = Integer.parseInt(scanner.nextLine());

			}

			catch (NumberFormatException e) {

				System.out.println("Enter a valid number");

				continue;

			}

			switch (option) {

			case 1:
				System.out.println("Enter the username");

				username = scanner.nextLine();

				System.out.println("Enter the password");

				password = scanner.nextLine();

				if (username.equals("venkatesh") /* userService.validate("adm"+username,password) */) {

					System.out.println("Logged in successfully");

					while (choice != 3) {

						System.out.println("1. view accounts to be approved");

						System.out.println("2. approve accounts");

						System.out.println("3. log out");

						try {

							choice = Integer.parseInt(scanner.nextLine());

						}

						catch (NumberFormatException e) {

							System.out.println("Enter a valid number");

							continue;

						}

						switch (choice) {

						case 1:

							List<WalletAccount> accounts = userService.accountsToBeApproved();

							if (accounts.size() == 0) {

								System.out.println("all are approved");

							}

							else {

								for (int i = 0; i < accounts.size(); i++) {

									System.out.println("Account Number : " + accounts.get(i).getAccountNo());

								}

							}

							break;

						case 2:

							System.out.println("Enter the account no");

							String value = scanner.nextLine();

							Integer lValue = 0;

							try {

								lValue = Integer.parseInt(value);

							}

							catch (NumberFormatException e) {

								System.out.println("Enter a valid no");

								continue;

							}

							try {

								userService.approveAccount((lValue));

							} catch (MyException e) {

								System.out.println(e.getMessage());
							}

							break;

						case 3:

							System.out.println("Logging you out......");

							choice = 3;

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

				username = scanner.nextLine();

				Integer id = 0;

				try {

					id = Integer.parseInt(username);

				}

				catch (NumberFormatException e) {

					System.out.println("Enter a valid id");

					continue;

				}

				System.out.println("Enter the password");

				password = scanner.nextLine();

				if (userService.validateLoginCredentials(id, password)) {

					WalletUser user;
					try {
						user = userService.getUser(id);
					} catch (MyException e2) {
						// TODO Auto-generated catch block
						System.out.println(e2.getMessage());
						continue;
					}

					System.out.println("Welcome " + user.getUserName());

					while (choice != 5) {
						WalletAccount account;
						try {
							user = userService.getUser(id);
							account = userService.getAccount(user.getAccount().getAccountNo());
						} catch (MyException e1) {
							// TODO Auto-generated catch block
							System.out.println(e1.getMessage());
						}

						System.out.println("1. To check account balance");

						System.out.println("2. To tranfer funds");

						System.out.println("3. Add amount to the wallet");

						System.out.println("4. Ministatement");

						System.out.println("5. Log out");

						try {
							choice = Integer.parseInt(scanner.nextLine());

						}

						catch (NumberFormatException e) {

							System.out.println("Enter a valid number");

							continue;

						}

						if (user.getAccount().getAccountStatus().toString().equals("Approved")) {

							switch (choice) {

							case 1:

								System.out.println("Your balance is:" + user.getAccount().getBalance());

								break;

							case 2:

								System.out.println("1. to transfer to different wallet user");
								System.out.println("2. to transfer to different bank account");
								int bankChoice = 0;
								try {
									bankChoice = Integer.parseInt(scanner.nextLine());
									if (bankChoice == 1) {
										try {
											System.out.println("Enter the phone number you want to tranfer to");
											String phoneNumber = scanner.nextLine();
											Integer fromAccount = user.getUserId();
											System.out.println("Enter the amount you want to transfer");
											Double amount = Double.parseDouble(scanner.nextLine());
											userService.transferAmount(fromAccount, phoneNumber, amount);
										} catch (NumberFormatException e) {
											System.out.println("Enter a valid userId");

										} catch (MyException e) {
											System.out.println(e.getMessage());
										}
									}

									else if (bankChoice == 2) {
										System.out.println("Enter the account no to be transferred");

										String str = scanner.nextLine();

										System.out.println("Enter the amount to be transferred");

										String amountToTransfer = scanner.nextLine();

										Double amount1 = 0.0;

										try {

											amount1 = Double.parseDouble(amountToTransfer);
											Integer toUser = Integer.parseInt(str);
											System.out.println("updated balance :" +

													userService.transferAmount(user.getUserId(), toUser, amount1));

										}

										catch (NumberFormatException e) {

											System.out.println("enter a valid amount");

										} catch (MyException e) {

											System.out.println(e.getMessage());

										}

									} else {
										System.out.println("Enter a valid choice");

									}

								}

								catch (NumberFormatException e) {
									System.out.println("Enter a valid choice");
								}

								break;

							case 3:

								System.out.println("Enter the amount to add");

								try {

									Double amount = Double.parseDouble(scanner.nextLine());

									System.out.println(
											"Your balance is " + userService.addAmount(user.getUserId(), amount));

								}

								catch (NumberFormatException e) {

									System.out.println("Enter a valid amount");

								} catch (MyException e) {

									System.out.println(e.getMessage());

								}

								break;

							case 4:

								try {

									// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

									LocalDateTime date = LocalDateTime.now();

									List<TransactionHistory> transactionHistories = userService
											.myTransactions(user.getUserId(), date

													, LocalDateTime.now());

									for (int i = 0; i < transactionHistories.size(); i++) {

										System.out.println(

												" Amount : " + transactionHistories.get(i).getAmount() +

														" tranferred to"

														+ " " + transactionHistories.get(i).getDescription()
														+ " Balance :" + transactionHistories.get(i).getBalance());

									}

								} catch (MyException e) {

									System.out.println(e.getMessage());

								} catch (Exception e) {

									System.out.println(e);

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

						else {

							System.out.println("Your account is waiting for the approval please" +

									"contact admin for further issues");

							choice = 5;

						}

					}

				}

				else {

					System.out.println("Invalid username and password");

				}

				break;

			case 3:

				System.out.println("Welcome user register your account here");

				boolean validInput = true;

				while (validInput) {

					System.out.println("Enter the userName");

					String userName = scanner.nextLine();

					System.out.println("Set your password");

					String password1 = scanner.nextLine();

					System.out.println("Confirm your password");

					String password2 = scanner.nextLine();

					if (password1.equals(password2) && password1.length() >= 8) {

						System.out.println("Enter the phone number");

						String number = scanner.nextLine();

						WalletUser user = new WalletUser();
						user.setUserName(userName);
						user.setUserPassword(password1);
						user.setPhoneNo(number);
						user.setAccount(new WalletAccount());
						try {

							user = userService.addWalletUser(user);

							System.out.println("Your user id is " + user.getUserId() + " " +

									"remember this for long time");

							System.out.println("Your account number is " + user.getAccount().getAccountNo());

							validInput = false;

						} catch (MyException e) {

							System.out.println(e.getMessage());

							validInput = false;

						}

					}

					else {

						if (!password1.equals(password2)) {

							System.out.println("passwords are not matching");

						}

						else {

							System.out.println("password should be 8 digits");

						}

					}

				}
				break;

			case 4:

				option = 4;

				System.out.println("Thankyou");

				break;

			default:
				System.out.println("Enter a valid option");
			}

		}

	}

}