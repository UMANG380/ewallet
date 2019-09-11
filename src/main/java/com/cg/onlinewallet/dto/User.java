package com.cg.onlinewallet.dto;

import java.math.BigInteger;

public class User<T> {
	
	BigInteger userId;
	String userName;
	String userPassword;
	static Double loyaltyBonus= 0.0;
	BigInteger phoneNo;
	T acc;
	
	
	public User() {
		
	}

	public User(BigInteger userId, String userName, String userPassword, BigInteger phoneNo,
			T acc) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.phoneNo = phoneNo;
		this.acc = acc;
		
	}

	

	public User(BigInteger id, String name, String password, String address, BigInteger phoneNo) {
		
		this.userId=id;
		this.userName=name;
		this.userPassword=password;
		this.phoneNo=phoneNo;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
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

	public T getAcc() {
		return acc;
	}

	public void setAcc(T acc) {
		this.acc = acc;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		@SuppressWarnings("unchecked")
		User<Account> other = (User<Account>) obj;
		
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
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", loyaltyBonus=" + loyaltyBonus + ", phoneNo=" + phoneNo + ", acc=" + acc + "]";
	}
	
	
	
	
	
	
	

}
