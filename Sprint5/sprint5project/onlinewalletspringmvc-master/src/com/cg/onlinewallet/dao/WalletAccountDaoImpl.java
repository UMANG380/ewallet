package com.cg.onlinewallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cg.onlinewallet.dto.WalletAccount;

public class WalletAccountDaoImpl implements WalletAccountDao {
	
	static EntityManagerFactory emf;
	static EntityManager em ;
	
	static {
		emf = Persistence.createEntityManagerFactory("mywallet");
		em= emf.createEntityManager();
	}


	@Override
	public WalletAccount addAccount() {
		// TODO Auto-generated method stub
		EntityTransaction trans = em.getTransaction();
		WalletAccount account =new WalletAccount();
		trans.begin();
		em.persist(account);
		trans.commit();
		return em.find(WalletAccount.class, account.getAccountNo());
	}

	@Override
	public WalletAccount getAccoun() {
		// TODO Auto-generated method stub
		return null;
	}

}
