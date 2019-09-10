package com.cg.onlinewallet.dto;

import java.math.BigInteger;
import java.util.Date;

public class Transaction {
	
	private String description;
	private BigInteger txId;
	private Date dateOfTx;
	private Double amount;
	private Double balance;
	
	Transaction(){
		
	}

	public Transaction(String description, BigInteger txId, Date dateOfTx, Double amount, Double balance) {
		super();
		this.description = description;
		this.txId = txId;
		this.dateOfTx = dateOfTx;
		this.amount = amount;
		this.balance = balance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getTxId() {
		return txId;
	}

	public void setTxId(BigInteger txId) {
		this.txId = txId;
	}

	public Date getDateOfTx() {
		return dateOfTx;
	}

	public void setDateOfTx(Date dateOfTx) {
		this.dateOfTx = dateOfTx;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((dateOfTx == null) ? 0 : dateOfTx.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((txId == null) ? 0 : txId.hashCode());
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
		if (dateOfTx == null) {
			if (other.dateOfTx != null)
				return false;
		} else if (!dateOfTx.equals(other.dateOfTx))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (txId == null) {
			if (other.txId != null)
				return false;
		} else if (!txId.equals(other.txId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transactions [description=" + description + ", txId=" + txId + ", dateOfTx=" + dateOfTx + ", amount="
				+ amount + ", balance=" + balance + "]";
	}

	
}
