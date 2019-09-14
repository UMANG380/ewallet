package com.cg.onlinewallet.dto;

import java.math.BigInteger;
import java.util.List;

public class WalletAccount {
	private final BigInteger accountNo;
	private Double balance=0.0;
	private List<Transaction> transactionList;
	private Status accountStatus;
	private static int count=0;
	/**
	 * 
	 */
	
	/**
	 * @param accountNo
	 * @param balance
	 * @param transactionList
	 */
	public WalletAccount(Double balance, List<Transaction> transactionList, Status status) {
		super();
		this.accountNo = BigInteger.valueOf(++count);
		this.balance = balance;
		this.transactionList = transactionList;
		this.accountStatus = status;
	}
	
	public BigInteger getAccountNo() {
		return accountNo;
	}
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
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
	

	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		WalletAccount.count = count;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((transactionList == null) ? 0 : transactionList.hashCode());
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
		WalletAccount other = (WalletAccount) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (accountStatus != other.accountStatus)
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (transactionList == null) {
			if (other.transactionList != null)
				return false;
		} else if (!transactionList.equals(other.transactionList))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WalletAccount [accountNo=" + accountNo + ", balance=" + balance + ", transactionList=" + transactionList
				+ ", accountStatus=" + accountStatus + "]";
	}
	

}

enum Status{
	Approved,Rejected,WaitingForApproval;
}
