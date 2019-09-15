package com.cg.onlinewallet.service;

import com.cg.onlinewallet.dao.WalletAccountDao;
import com.cg.onlinewallet.dao.WalletAccountDaoImpl;
import com.cg.onlinewallet.dao.WalletUserDao;
import com.cg.onlinewallet.dao.WalletUserDaoImpl;
import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.exception.MyException;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class WalletUserServiceImpl implements WalletUserService {

    private WalletUserDao userDao = new WalletUserDaoImpl();
    @Override
    public Double getBalance(BigInteger userId) throws MyException {
        Validate.validateId(userId);
        return userDao.getBalance(userId);
    }

    @Override
    public Double addAmount(BigInteger userId, Double amount) throws MyException {
        Validate.validateId(userId);
        if(amount>0){
            return userDao.addAmount(userId,amount);
        }
        throw new MyException("amount can no be null");
    }

    @Override
    public Double transferAmount(BigInteger userId, String toAccount, Double amount) throws MyException {
        Validate.validateId(userId);
        Double balance=userDao.transferAmount(userId,toAccount,amount);
        if(balance.equals(-1.0)){
            throw new MyException("Insufficient funds");
        }
        return balance;
    }

    @Override
    public List<Transaction> myTransactions(BigInteger userId, LocalDateTime date1, LocalDateTime date2) throws MyException {
        Validate.validateId(userId);
        Validate.validateDate(date2);
        return userDao.myTransactions(userId,date1,date2);
    }

    @Override
    public List<WalletAccount> accountsToBeApproved() {
        WalletAccountDao accountDao = new WalletAccountDaoImpl();
        return accountDao.accountsToBeApproved();
    }

    @Override
    public WalletAccount approveAccount(BigInteger accountNo) throws MyException {
        WalletAccountDao accountDao = new WalletAccountDaoImpl();
        Validate.validateId(accountNo);
        return accountDao.approveAccount(accountNo);
    }

    @Override
    public WalletUser addWalletUser(WalletUser user) throws MyException {
        Validate.validateDuplicate(user);
        Validate.validatePhoneNumber(user.getPhoneNo());
        userDao.addWalletUser(user);
        return user;
    }

    @Override
    public boolean validateLoginCredentials(BigInteger userId, String password) {
        WalletUser user = userDao.searchUser(userId);
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
    public WalletUser getUser(BigInteger userId) {
        return userDao.searchUser(userId);
    }
}
