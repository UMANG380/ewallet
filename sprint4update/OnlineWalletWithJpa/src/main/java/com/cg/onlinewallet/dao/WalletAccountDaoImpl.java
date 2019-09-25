package com.cg.onlinewallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cg.onlinewallet.dto.WalletAccount;

public class WalletAccountDaoImpl implements WalletAccountDao {
	
	private static EntityManagerFactory entityManagerFactory;
	 EntityManager entityManager ;
	static {
		entityManagerFactory =EntityManagerFactoryUtil.getEntityManagerFactory();
	}
	{		
		entityManager= entityManagerFactory.createEntityManager();
	}

	@Override
	public WalletAccount addAccount() {
		// TODO Auto-generated method stub
		EntityTransaction trans = entityManager.getTransaction();
		WalletAccount account =new WalletAccount();
		trans.begin();
		entityManager.persist(account);
		trans.commit();
		return entityManager.find(WalletAccount.class, account.getAccountNo());
	}

	@Override
	public WalletAccount getAccoun() {
		// TODO Auto-generated method stub
		return null;
	}

}
