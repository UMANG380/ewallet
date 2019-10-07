package com.cg.onlinewallet.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.onlinewallet.dto.Status;
import com.cg.onlinewallet.dto.TransactionHistory;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;

@Repository("userDao")
public class WalletUserDaoImpl implements WalletUserDao {
	 
	@PersistenceContext
	 EntityManager em ;

	@Override
	public WalletUser addUser(WalletUser user) {
		WalletAccount account = new WalletAccount();
		user.setAccount(account);
		em.persist(user);
		return user;
	}

	@Override
	public Double addAmount(Integer userId,Double amount) {
		// TODO Auto-generated method stub
		WalletUser user = em.find(WalletUser.class, userId);
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAmount(amount);
		user.getAccount().setBalance(amount+user.getAccount().getBalance());
		transactionHistory.setBalance(user.getAccount().getBalance());
		transactionHistory.setDateOfTransaction(LocalDateTime.now());
		transactionHistory.setAccount(em.find(WalletAccount.class, user.getAccount().getAccountNo()));
		transactionHistory.setDescription("myself");
		//em.merge(user);
		em.persist(transactionHistory);
		return em.find(WalletUser.class,userId).getAccount().getBalance();
	}

	@Override
	public Double transferAmount(Integer fromUserId, String phoneNumber, Double amount) {
		// TODO Auto-generated method stub
		
		String sql = "select u from WalletUser u where u.phoneNo= :first";
		Query query = em.createQuery(sql);
		query.setParameter("first", phoneNumber);
	
		WalletUser toUser = (WalletUser) query.getSingleResult();
		toUser = em.find(WalletUser.class, toUser.getUserId());
		WalletUser fromUser = em.find(WalletUser.class, fromUserId);
		fromUser.getAccount().setBalance(fromUser.getAccount().getBalance()-amount);
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAccount(fromUser.getAccount());
		transactionHistory.setBalance(fromUser.getAccount().getBalance());
		transactionHistory.setAmount(amount);
		transactionHistory.setDateOfTransaction(LocalDateTime.now());
		transactionHistory.setDescription("transferred to phone number "+phoneNumber);
		em.merge(fromUser);
		em.persist(transactionHistory);
		TransactionHistory transaction1 = new TransactionHistory();
		toUser.getAccount().setBalance(toUser.getAccount().getBalance()+amount);
		transaction1.setAccount(toUser.getAccount());
		transaction1.setBalance(toUser.getAccount().getBalance());
		transaction1.setAmount(amount);
		transaction1.setDescription("received from "+fromUser.getPhoneNo());
		transaction1.setDateOfTransaction(LocalDateTime.now());
		//em.merge(toUser);
		//em.merge(toUser.getAccount());
		em.persist(transaction1);
	
		return fromUser.getAccount().getBalance();
	}

	@Override
	public Double transferAmount(Integer fromUserId, Integer accountNo, Double amount) {
		// TODO Auto-generated method stub
		
		WalletUser fromUser = em.find(WalletUser.class, fromUserId);
		fromUser.getAccount().setBalance(fromUser.getAccount().getBalance()-amount);
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAccount(fromUser.getAccount());
		transactionHistory.setBalance(fromUser.getAccount().getBalance());
		transactionHistory.setAmount(amount);
		transactionHistory.setDateOfTransaction(LocalDateTime.now());
		transactionHistory.setDescription("transferred to accountNo "+accountNo);
		//em.merge(fromUser);
		//em.merge(fromUser.getAccount());
		em.persist(transactionHistory);
		//em.flush();
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
	
	public List<TransactionHistory> getTransactions(Integer accountId,LocalDateTime fromDate,
			LocalDateTime toDate){
		//em.refresh(TransactionHistory.class);
//		WalletAccount account = em.find(WalletAccount.class, accountId);		
//		return account.getTransactionList();
		WalletAccount account = em.find(WalletAccount.class, accountId);
		//String sql = "select a from TransactionHistory a";
		String sql = "select a from TransactionHistory a where a.walletAccount= :first";
		Query query = em.createQuery(sql);
		query.setParameter("first", account);
		List<TransactionHistory> myTransactions = query.getResultList();
		System.out.println(myTransactions.size()+" "+fromDate);
		List<TransactionHistory> retTransactions = new ArrayList<TransactionHistory>();
		for(int i=0;i<myTransactions.size();i++) {
			if(myTransactions.get(i).getDateOfTransaction()!=null&&myTransactions.get(i).getDateOfTransaction().compareTo(fromDate)>=0
			   &&myTransactions.get(i).getDateOfTransaction().compareTo(toDate)<=0) {
				retTransactions.add(myTransactions.get(i));
			}
		}
		return retTransactions;
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
		WalletAccount account = em.find(WalletAccount.class, accountId);
		if(account==null) {
			throw new RuntimeException("no account");
		}
		account.setAccountStatus(Status.Approved);
		return account;
	}

	@Override
	public WalletAccount getAccount(Integer accountNo) {
		// TODO Auto-generated method stub
		return em.find(WalletAccount.class, accountNo);
	}
	
	
}
