package com.cg.onlinewallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.onlinewallet.dto.TransactionHistory;
import com.cg.onlinewallet.dto.WalletAccount;

public class TransactionDaoImpl  {
	
	private static EntityManagerFactory entityManagerFactory;
	 EntityManager entityManager ;
	static {
		entityManagerFactory =EntityManagerFactoryUtil.getEntityManagerFactory();
	}
	{		
		entityManager= entityManagerFactory.createEntityManager();
	}

}
