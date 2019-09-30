package com.cg.onlinewallet.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
public class Transaction {
	
	@Id
	@GeneratedValue
	
	private Integer transactionId;
	private String description;
	@Past
	private Date dateOfTransaction;
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Amount should be positive")
	private Double amount;
	private Double balance;
	@ManyToOne(cascade = CascadeType.ALL )
	@JoinColumn
	private WalletAccount walletAccount;
		
	public Transaction() {
		
	}

	public Transaction(String description, Integer transactionId, Date dateOfTransaction, Double amount,
			Double balance, WalletAccount account) {
		super();
		this.description = description;
		this.transactionId = transactionId;
		this.dateOfTransaction = dateOfTransaction;
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

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

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
		return "Transaction [description=" + description + ", transactionId=" + transactionId 
				+", amount=" + amount + ", balance=" + balance + ", account=" + walletAccount + "]";
	}
	
	


}
