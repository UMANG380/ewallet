package com.cg.onlinewallet.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.onlinewallet.dao.WalletUserDao;
import com.cg.onlinewallet.dao.WalletUserDaoImpl;
import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.exception.MyException;

public class WalletUserServiceImpl implements WalletUserService {
	
	private static WalletUserDao dao = new WalletUserDaoImpl();

	@Override
	public Double getBalance(Integer userId) throws MyException {
		Validate.validateId(userId);
		return dao.checkBalance(userId);
	}

	@Override
	public Double addAmount(Integer userId, Double amount) throws MyException {
		Validate.validateId(userId);
        if(amount>0){
            return dao.addAmount(userId, amount);
        }
        throw new MyException("amount can not be less than zero");
	}

	@Override
	public Double transferAmount(Integer userId, String phoneNumber, Double amount) throws MyException {
		Validate.validateId(userId);
		if(amount<0) {
			throw new MyException("amount can not be less than zero");
		}
		WalletUser user = dao.getUser(userId);
		
		if(user.getAccount().getBalance().compareTo(amount)<0) {
			throw new MyException("Insufficient funds");
		}
		return dao.transferAmount(userId, phoneNumber, amount);
	}

	@Override
	public List<Transaction> myTransactions(Integer userId, LocalDateTime date1, LocalDateTime date2)
			throws MyException {

        Validate.validateDate(date2);
        WalletUser user = dao.getUser(userId);
		return dao.getTransactions(user.getAccount().getAccountNo());
	}

	@Override
	public List<WalletAccount> accountsToBeApproved() {
		// TODO Auto-generated method stub
		return dao.getAccountsToApprove();
	}

	@Override
	public WalletAccount approveAccount(Integer accountNo) throws MyException {
		// TODO Auto-generated method stub
		return dao.approveAccount(accountNo);
	}

	@Override
	public WalletUser addWalletUser(WalletUser user) throws MyException {
		// TODO Auto-generated method stub
		Validate.validateDuplicate(user);
        Validate.validatePhoneNumber(user.getPhoneNo());
		return dao.addUser(user);
	}

	@Override
	public boolean validateLoginCredentials(Integer userId, String password) {
		// TODO Auto-generated method stub
		 WalletUser user = dao.getUser(userId);
	        if(user!=null){
	            if(user.getUserPassword().equals(password)){
	                return true;
	            }
	            else{
	                return false;
	            }
	        }
		return false;
	}

	@Override
	public WalletUser getUser(Integer userId) throws MyException {
		// TODO Auto-generated method stub
		Validate.validateId(userId);
		return dao.getUser(userId);
	}

	@Override
	public Double transferAmount(Integer fromUserId, Integer toAccountNo, Double amount) throws MyException {
		// TODO Auto-generated method stub
		Validate.validateId(fromUserId);
		if(amount<0) {
			throw new MyException("amount can not be less than zero");
		}
		WalletUser user = dao.getUser(fromUserId);
		
		if(user.getAccount().getBalance().compareTo(amount)<0) {
			throw new MyException("Insufficient funds");
		}
		return dao.transferAmount(fromUserId, toAccountNo, amount);
	}

	@Override
	public WalletAccount getAccount(Integer accountNo) {
		// TODO Auto-generated method stub
		return dao.getAccount(accountNo);
	}

}
