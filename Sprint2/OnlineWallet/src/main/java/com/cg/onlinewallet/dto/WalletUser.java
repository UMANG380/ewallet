package com.cg.onlinewallet.dto;

import java.math.BigInteger;

public class WalletUser {
	private final BigInteger userId;
	private String userName;
	private String userPassword;
	private static int count=0;
	
	
	BigInteger phoneNo;
	WalletAccount account;
	
	/**
	 * 
	 */
	/**
	 * @param userId
	 * @param userName
	 * @param userPassword
	 * @param phoneNo
	 * @param account
	 */
	public WalletUser(String userName, String userPassword, BigInteger phoneNo,
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
	
	public BigInteger getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(BigInteger phoneNo) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalletUser other = (WalletUser) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "WalletUser [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", phoneNo=" + phoneNo + ", account=" + account + "]";
	}
	

	


}
