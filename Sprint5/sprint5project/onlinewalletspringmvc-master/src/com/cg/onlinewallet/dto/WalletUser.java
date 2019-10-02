package com.cg.onlinewallet.dto;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
public class WalletUser {
	@Id
	@GeneratedValue
	private  Integer userId; 
    @Size(min=3,message="size must be three")
	private String userName;
	private String userPassword;
	private String phoneNo;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_no",referencedColumnName = "accountNo")
	WalletAccount account;
	
	public WalletUser() {
		
	}

	public WalletUser(Integer userId, String userName, String userPassword, String phoneNo, WalletAccount account) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.phoneNo = phoneNo;
		this.account = account;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	@Override
	public String toString() {
		return "WalletUser [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", phoneNo=" + phoneNo + ", account=" + account + "]";
	}
	


}
