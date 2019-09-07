package com.cg.onlinewallet.dto;

import java.math.BigInteger;

public class User {
	
	BigInteger userId;
	String userName;
	String userPassword;
	Double loyaltyBonus;
	BigInteger phoneNo;
	Account acc;
	String role;
	
	public User() {
		
	}

	public User(BigInteger userId, String userName, String userPassword, Double loyaltyBonus, BigInteger phoneNo,
			Account acc, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.loyaltyBonus = loyaltyBonus;
		this.phoneNo = phoneNo;
		this.acc = acc;
		this.role = role;
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

	public Double getLoyaltyBonus() {
		return loyaltyBonus;
	}

	public void setLoyaltyBonus(Double loyaltyBonus) {
		this.loyaltyBonus = loyaltyBonus;
	}

	public BigInteger getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(BigInteger phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Account getAcc() {
		return acc;
	}

	public void setAcc(Account acc) {
		this.acc = acc;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loyaltyBonus == null) ? 0 : loyaltyBonus.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
		if (loyaltyBonus == null) {
			if (other.loyaltyBonus != null)
				return false;
		} else if (!loyaltyBonus.equals(other.loyaltyBonus))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
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
				+ ", loyaltyBonus=" + loyaltyBonus + ", phoneNo=" + phoneNo + ", acc=" + acc + ", role=" + role + "]";
	}
	
	
	
	
	
	
	

}
