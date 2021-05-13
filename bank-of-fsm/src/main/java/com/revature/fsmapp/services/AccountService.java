package com.revature.fsmapp.services;

import com.revature.fsmapp.daos.AccountDAO;
import com.revature.fsmapp.exceptions.AccountNotFoundException;
import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.models.Transaction;
import com.revature.fsmapp.util.collection.List;

public class AccountService implements Service{
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    @Override
    public void init() {

    }

    public Account openAccount(AppUser user,String pin, double balance) {
        int accountID = -1;
        Account tempAccount;
        if(verifyValidOpeningBalance(balance)&& verifyValidPin(pin)){
            accountID = accountDAO.openAccount(user.getUserID(),pin,balance);
            if(accountDAO.accountOpen(accountID)){
                tempAccount = new Account(balance,accountID,pin);
                user.setActiveAccount(tempAccount);
                return tempAccount;
            }
        }
        return new Account();
    }

    private boolean verifyValidOpeningBalance(double balance){
        return balance > 0;
    }

    private boolean verifyValidPin(String pin){
        return (pin.length() == 4);
    }

    public boolean linkAccount(AppUser appUser, int accountNumber) {
        if(accountDAO.accountOpen(accountNumber)){
            return accountDAO.linkAccount(appUser.getUserID(),accountNumber);
        }
        return false;
    }

    public List<Account> getUserAccounts(AppUser user){
        return accountDAO.getAccountsByUserID(user);
    }

    public Account validateAccount(List<Account> accounts, int accountNumber) throws AccountNotFoundException {
        for (Account account:accounts) {
            if(account.getAccountNumber()==accountNumber){
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    public Transaction recordTransaction(String sender, int sendersID, int recipientID, double amount){
        return accountDAO.saveTransaction(sender, sendersID, recipientID, amount).get();
    }

    public boolean accountExists(int accountID){
        return accountDAO.accountExists(accountID);
    }

    public void subtractBalance(double amount, int accountID,Account account) {

    }

    public void addBalance(double amount, int recipientID,Account account) {
    }
}
