package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.onlinewallet.dto.Status;
import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;

public class WalletUserDaoImpl implements WalletUserDao {
	
	static EntityManagerFactory emf;
	static EntityManager em ;
	
	static {
		emf = Persistence.createEntityManagerFactory("mywallet");
		em= emf.createEntityManager();
	}

	@Override
	public WalletUser addUser(WalletUser user) {
		
		WalletAccountDao accountDao = new WalletAccountDaoImpl();
		// TODO Auto-generated method stub
//		List<WalletUser> users = getAllUsers();
//		for(int i=0;i<users.size();i++) {
//			if(users.get(i).getPhoneNo().equals(user.getPhoneNo())) {
//				throw new MyException("User exists try to login");
//			}
//		}
		WalletAccount account = new WalletAccount();
		user.setAccount(account);
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(user);
		trans.commit();
		return user;
	}

	@Override
	public Double addAmount(Integer userId,Double amount) {
		// TODO Auto-generated method stub
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		WalletUser user = em.find(WalletUser.class, userId);
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		user.getAccount().setBalance(amount+user.getAccount().getBalance());
		transaction.setBalance(user.getAccount().getBalance());
	//	transaction.setDateOfTransaction(LocalDateTime.now());
		transaction.setAccount(em.find(WalletAccount.class, user.getAccount().getAccountNo()));
		transaction.setDescription("myself");
		em.merge(user);
		em.persist(transaction);
		
		trans.commit();
		return em.find(WalletUser.class,userId).getAccount().getBalance();
	}

	@Override
	public Double transferAmount(Integer fromUserId, String phoneNumber, Double amount) {
		// TODO Auto-generated method stub
		EntityTransaction trans = em.getTransaction();
		String sql = "select u from WalletUser u where u.phoneNo= :first";
		Query query = em.createQuery(sql);
		query.setParameter("first", phoneNumber);
		trans.begin();
		WalletUser toUser = (WalletUser) query.getSingleResult();
		toUser = em.find(WalletUser.class, toUser.getUserId());
		WalletUser fromUser = em.find(WalletUser.class, fromUserId);
		fromUser.getAccount().setBalance(fromUser.getAccount().getBalance()-amount);
		Transaction transaction = new Transaction();
		transaction.setAccount(fromUser.getAccount());
		transaction.setBalance(fromUser.getAccount().getBalance());
		transaction.setAmount(amount);
		transaction.setDescription("transferred to phone number "+phoneNumber);
		em.merge(fromUser);
		em.persist(transaction);
		trans.commit();
		EntityTransaction trans1 = em.getTransaction();
		trans1.begin();
		Transaction transaction1 = new Transaction();
		toUser.getAccount().setBalance(toUser.getAccount().getBalance()+amount);
		transaction1.setAccount(toUser.getAccount());
		transaction1.setBalance(toUser.getAccount().getBalance());
		transaction1.setAmount(amount);
		transaction1.setDescription("received from "+fromUser.getPhoneNo());
		em.merge(toUser);
		em.persist(transaction1);
		trans1.commit();
		return fromUser.getAccount().getBalance();
	}

	@Override
	public Double transferAmount(Integer fromUserId, Integer accountNo, Double amount) {
		// TODO Auto-generated method stub
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		WalletUser fromUser = em.find(WalletUser.class, fromUserId);
		fromUser.getAccount().setBalance(fromUser.getAccount().getBalance()-amount);
		Transaction transaction = new Transaction();
		transaction.setAccount(fromUser.getAccount());
		transaction.setBalance(fromUser.getAccount().getBalance());
		transaction.setAmount(amount);
		transaction.setDescription("transferred to accountNo "+accountNo);
		em.merge(fromUser);
		em.merge(fromUser.getAccount());
		em.persist(transaction);
		em.flush();
		trans.commit();
		return fromUser.getAccount().getBalance();
	}

	@Override
	public Double checkBalance(Integer userId) {
		// TODO Auto-generated method stub
		return em.find(WalletUser.class, userId).getAccount().getBalance();
	}

	@Override
	public List<WalletUser> getAllUsers() {
		// TODO Auto-generated method stub
		String sql ="select u from WalletUser u ";
		Query query = em.createQuery(sql);
		return query.getResultList();
	}

	@Override
	public WalletUser getUser(Integer userId) {
		// TODO Auto-generated method stub
		return em.find(WalletUser.class, userId);
	}
	
	public List<Transaction> getTransactions(Integer accountId){
		WalletAccount account = em.find(WalletAccount.class, accountId);
		return account.getTransactionList();
	}

	@Override
	public List<WalletAccount> getAccountsToApprove() {
		// TODO Auto-generated method stub
	    List<WalletUser> users = getAllUsers();
	    List<WalletAccount> notApproved = new ArrayList<WalletAccount>();
	    for(int i=0;i<users.size();i++) {
	    	if(users.get(i).getAccount().getAccountStatus().toString().equals("WatingForApproval")) {
	    		notApproved.add(users.get(i).getAccount());
	    	}
	    }
		return notApproved;
	}

	@Override
	public WalletAccount approveAccount(Integer accountId) {
		// TODO Auto-generated method stub
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		WalletAccount account = em.find(WalletAccount.class, accountId);
		account.setAccountStatus(Status.Approved);
		trans.commit();
		return account;
	}

	@Override
	public WalletAccount getAccount(Integer accountNo) {
		// TODO Auto-generated method stub
		return em.find(WalletAccount.class, accountNo);
	}
	
	
}
