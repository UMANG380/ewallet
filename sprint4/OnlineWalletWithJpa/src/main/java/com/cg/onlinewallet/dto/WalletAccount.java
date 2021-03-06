package com.cg.onlinewallet.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class WalletAccount {
	@Id
	@GeneratedValue
	private Integer accountNo;
	private Double balance = 0.0;
	@OneToMany(mappedBy = "walletAccount",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Transaction> transactionList;
	@Enumerated(EnumType.STRING)
	private Status accountStatus;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
	private WalletUser user;
	
	public WalletAccount() {
		this.balance=0.0;
		this.accountStatus=Status.WatingForApproval;
		this.transactionList=new ArrayList<Transaction>(0);
	}
	
	public WalletAccount(Integer accountNo, List<Transaction> transactionList) {
		super();
		this.accountNo = accountNo;
		this.balance = 0.0;
		this.transactionList = transactionList;
		this.accountStatus = Status.WatingForApproval;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@OneToMany(mappedBy = "walletAccount",fetch = FetchType.LAZY)
	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public Status getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Status accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "WalletAccount [accountNo=" + accountNo + ", balance=" + balance + ", transactionList=" + transactionList
				+ ", accountStatus=" + accountStatus + "]";
	}
	
	
	

}
