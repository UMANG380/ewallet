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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class WalletAccount {
	@Id
	@GeneratedValue
	@NotEmpty(message="Account No. shouldn't be empty ")
	private Integer accountNo;
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Balance should be positive")
	private Double balance = 0.0;
	@OneToMany(mappedBy = "walletAccount",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<TransactionHistory> transactionList;
	@Enumerated(EnumType.STRING)
	private Status accountStatus;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
	private WalletUser user;
	
	public WalletAccount() {
		this.balance=0.0;
		this.accountStatus=Status.WatingForApproval;
		this.transactionList=new ArrayList<TransactionHistory>(0);
	}
	
	public WalletAccount(Integer accountNo, List<TransactionHistory> transactionList) {
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
	public List<TransactionHistory> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionHistory> transactionList) {
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
