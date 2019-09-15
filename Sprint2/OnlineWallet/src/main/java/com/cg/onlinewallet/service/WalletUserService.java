package com.cg.onlinewallet.service;

import com.cg.onlinewallet.dto.Transaction;
import com.cg.onlinewallet.dto.WalletAccount;
import com.cg.onlinewallet.dto.WalletUser;
import com.cg.onlinewallet.exception.MyException;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface WalletUserService {

    public Double getBalance(BigInteger userId) throws MyException;
    public Double addAmount(BigInteger userId,Double amount) throws MyException;
    public Double transferAmount(BigInteger userId,String toAccount,Double amount) throws MyException;
    public List<Transaction> myTransactions(BigInteger userId, LocalDateTime date1, LocalDateTime date2) throws MyException;
    public List<WalletAccount> accountsToBeApproved();
    public WalletAccount approveAccount(BigInteger accountNo) throws MyException;
    public WalletUser addWalletUser(WalletUser user) throws MyException;
    public boolean validateLoginCredentials(BigInteger userId,String password);
    public WalletUser getUser(BigInteger userId);
}
