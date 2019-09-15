package com.cg.onlinewallet.dto;

import java.math.BigInteger;
import java.util.Objects;

public class WalletUser {
	private final BigInteger userId;
	private String userName;
	private String userPassword;
	private static int count=0;
	
	
	String phoneNo;
	WalletAccount account;
	
	/**
	 * 
	 */
	/**
	 * @param
	 * @param userName
	 * @param userPassword
	 * @param phoneNo
	 * @param account
	 */
	public WalletUser(String userName, String userPassword, String phoneNo,
			WalletAccount account) {
		super();
		this.userId = BigInteger.valueOf(++count);
		this.userName = userName;
		this.userPassword = userPassword;
		this.phoneNo = phoneNo;
		this.account = account;
	}


	public BigInteger getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public WalletAccount getAccount() {
		return account;
	}
	public void setAccount(WalletAccount account) {
		this.account = account;
	}

	public static int getCount() {
		return count;
	}


	public static void setCount(int count) {
		WalletUser.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WalletUser)) return false;
		WalletUser user = (WalletUser) o;
		return getPhoneNo().equals(user.getPhoneNo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPhoneNo());
	}

	@Override
	public String toString() {
		return "WalletUser [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", phoneNo=" + phoneNo + ", account=" + account + "]";
	}
	

	


}
