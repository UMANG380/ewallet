package com.cg.onlinewallet.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.cg.onlinewallet.dto.Status;
import com.cg.onlinewallet.dto.TransactionHistory;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;

public class WalletUserDaoImpl implements WalletUserDao {
	
	private static EntityManagerFactory entityManagerFactory;
	 EntityManager entityManager ;
	static {
		entityManagerFactory =EntityManagerFactoryUtil.getEntityManagerFactory();
	}
	{		
		entityManager= entityManagerFactory.createEntityManager();
	}

	@Override
	public WalletUser addUser(WalletUser user) {
		WalletAccount account = new WalletAccount();
		user.setAccount(account);
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(user);
		trans.commit();
		return user;
	}

	@Override
	public Double addAmount(Integer userId,Double amount) {
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		WalletUser user = entityManager.find(WalletUser.class, userId);
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAmount(amount);
		user.getAccount().setBalance(amount+user.getAccount().getBalance());
		transactionHistory.setBalance(user.getAccount().getBalance());
		transactionHistory.setDateOfTransaction(LocalDateTime.now());
		transactionHistory.setAccount(entityManager.find(WalletAccount.class, user.getAccount().getAccountNo()));
		transactionHistory.setDescription("myself");
		entityManager.persist(transactionHistory);
		
		trans.commit();
		return entityManager.find(WalletUser.class,userId).getAccount().getBalance();
	}

	@Override
	@Transactional
	public Double transferAmount(Integer fromUserId, String phoneNumber, Double amount) {
		EntityTransaction trans = entityManager.getTransaction();
		String sql = "select u from WalletUser u where u.phoneNo= :first";
		Query query = entityManager.createQuery(sql);
		query.setParameter("first", phoneNumber);
		trans.begin();
		WalletUser toUser = (WalletUser) query.getSingleResult();
		toUser = entityManager.find(WalletUser.class, toUser.getUserId());
		WalletUser fromUser = entityManager.find(WalletUser.class, fromUserId);
		fromUser.getAccount().setBalance(fromUser.getAccount().getBalance()-amount);
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAccount(fromUser.getAccount());
		transactionHistory.setBalance(fromUser.getAccount().getBalance());
		transactionHistory.setAmount(amount);
		transactionHistory.setDateOfTransaction(LocalDateTime.now());
		transactionHistory.setDescription("transferred to phone number "+phoneNumber);
		entityManager.merge(fromUser);
		entityManager.persist(transactionHistory);
		TransactionHistory transaction1 = new TransactionHistory();
		toUser.getAccount().setBalance(toUser.getAccount().getBalance()+amount);
		transaction1.setAccount(toUser.getAccount());
		transaction1.setBalance(toUser.getAccount().getBalance());
		transaction1.setAmount(amount);
		transaction1.setDescription("received from "+fromUser.getPhoneNo());
		transaction1.setDateOfTransaction(LocalDateTime.now());
		entityManager.persist(transaction1);
		trans.commit();
		return fromUser.getAccount().getBalance();
	}

	@Override
	@Transactional
	public Double transferAmount(Integer fromUserId, Integer accountNo, Double amount) {
		// TODO Auto-generated method stub
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		WalletUser fromUser = entityManager.find(WalletUser.class, fromUserId);
		fromUser.getAccount().setBalance(fromUser.getAccount().getBalance()-amount);
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAccount(fromUser.getAccount());
		transactionHistory.setBalance(fromUser.getAccount().getBalance());
		transactionHistory.setAmount(amount);
		transactionHistory.setDateOfTransaction(LocalDateTime.now());
		transactionHistory.setDescription("transferred to accountNo "+accountNo);
		entityManager.persist(transactionHistory);
		trans.commit();
		return fromUser.getAccount().getBalance();
	}

	@Override
	public Double checkBalance(Integer userId) {
		return entityManager.find(WalletUser.class, userId).getAccount().getBalance();
	}

	@Override
	public List<WalletUser> getAllUsers() {
		String sql ="select u from WalletUser u ";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}

	@Override
	public WalletUser getUser(Integer userId) {
		return entityManager.find(WalletUser.class, userId);
	}
	
	public List<TransactionHistory> getTransactions(Integer accountId){
		//entityManager.refresh(TransactionHistory.class);
//		WalletAccount account = entityManager.find(WalletAccount.class, accountId);		
//		return account.getTransactionList();
		WalletAccount account = entityManager.find(WalletAccount.class, accountId);
		//String sql = "select a from TransactionHistory a";
		String sql = "select a from TransactionHistory a where a.walletAccount= :first";
		Query query = entityManager.createQuery(sql);
		query.setParameter("first", account);
		List<TransactionHistory> history =query.getResultList();
		List<TransactionHistory> myTransactions = new ArrayList<TransactionHistory>();
		for(int i=0;i<history.size();i++) {
			if(history.get(i).getAccount().getAccountNo()==accountId) {
				myTransactions.add(history.get(i));
				
			}
		}
		return myTransactions;
	}

	@Override
	public List<WalletAccount> getAccountsToApprove() {
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
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		WalletAccount account = entityManager.find(WalletAccount.class, accountId);
		account.setAccountStatus(Status.Approved);
		trans.commit();
		return account;
	}

	@Override
	public WalletAccount getAccount(Integer accountNo) {
		return entityManager.find(WalletAccount.class, accountNo);
	}
	
	
}
