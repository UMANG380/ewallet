package com.cg.onlinewallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.onlinewallet.dto.TransactionHistory;
import com.cg.onlinewallet.dto.WalletAccount;

public class TransactionDaoImpl  {
	
	static EntityManagerFactory emf;
	static EntityManager em ;
	
	static {
		emf = Persistence.createEntityManagerFactory("mywallet");
		em= emf.createEntityManager();
	}

//
//	@Override
//	public TransactionHistory addTransaction(String description) {
//		// TODO Auto-generated method stub
//		TransactionHistory transaction = new TransactionHistory();
//		transaction.setAmount(amount);
//		transaction.setBalance(user.getAccount().getBalance());
//	//	transaction.setDateOfTransaction(LocalDateTime.now());
//		transaction.setAccount(em.find(WalletAccount.class, user.getAccount().getAccountNo()));
//		transaction.setDescription(description);
//		return null;
//	}
//	
	

}
