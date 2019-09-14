package com.cg.onlinewallet.dto;

import java.math.BigInteger;
import java.util.Date;

public class Transaction {
	private String description;
	private BigInteger transactionId;
	private Date dateOfTransaction;
	private Double amount;
	private Double balance;
	/**
	 * 
	 */
	public Transaction() {
		super();
	}
	/**
	 * @param description
	 * @param transactionId
	 * @param dateOfTransaction
	 * @param amount
	 * @param balance
	 */
	public Transaction(String description, BigInteger transactionId, Date dateOfTransaction, Double amount, Double balance) {
		super();
		this.description = description;
		this.transactionId = transactionId;
		this.dateOfTransaction = dateOfTransaction;
		this.amount = amount;
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((dateOfTransaction == null) ? 0 : dateOfTransaction.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
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
		Transaction other = (Transaction) obj; 
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (dateOfTransaction == null) {
			if (other.dateOfTransaction != null)
				return false;
		} else if (!dateOfTransaction.equals(other.dateOfTransaction))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [description=" + description + ", transactionId=" + transactionId + ", dateOfTransaction=" + dateOfTransaction + ", amount="
				+ amount + ", balance=" + balance + "]";
	}
	
	
	

}
