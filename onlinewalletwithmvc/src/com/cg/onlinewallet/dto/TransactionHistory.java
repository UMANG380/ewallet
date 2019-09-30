package com.cg.onlinewallet.dto;

import java.math.BigInteger;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class TransactionHistory {
	
	@Id
	@GeneratedValue
	private Integer transactionId;
	private String description;
	//private Date dateOfTransaction;
	private Double amount;
	private Double balance;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	@JoinColumn
	private WalletAccount walletAccount;
		
	public TransactionHistory() {
		
	}

	public TransactionHistory(String description, Integer transactionId, Date dateOfTransaction, Double amount,
			Double balance, WalletAccount account) {
		super();
		this.description = description;
		this.transactionId = transactionId;
		//this.dateOfTransaction = dateOfTransaction;
		this.amount = amount;
		this.balance = balance;
		this.walletAccount = account;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

//	public Date getDateOfTransaction() {
//		return dateOfTransaction;
//	}
//
//	public void setDateOfTransaction(Date dateOfTransaction) {
//		this.dateOfTransaction = dateOfTransaction;
//	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public WalletAccount getAccount() {
		return walletAccount;
	}

	public void setAccount(WalletAccount account) {
		this.walletAccount = account;
	}

	@Override
	public String toString() {
		return "TransactionHistory [description=" + description + ", transactionId=" + transactionId 
				+", amount=" + amount + ", balance=" + balance + ", account=" + walletAccount + "]";
	}
	
	


}
