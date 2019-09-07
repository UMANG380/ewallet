package com.cg.onlinewallet.dto;

import java.math.BigInteger;
import java.util.List;

public class Account {
	
	BigInteger accountNo;
	Double balance;
	List<Transactions> transactionList;
	
	public Account() {
		
	}

	public Account(BigInteger accountNo, Double balance, List<Transactions> transactionList) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.transactionList = transactionList;
	}

	public BigInteger getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(BigInteger accountNo) {
		this.accountNo = accountNo;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Transactions> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transactions> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
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
		Account other = (Account) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", balance=" + balance + "]";
	}
	
	
	

}
