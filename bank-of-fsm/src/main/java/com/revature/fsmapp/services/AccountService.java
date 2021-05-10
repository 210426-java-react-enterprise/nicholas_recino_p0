package com.revature.fsmapp.services;

import com.revature.fsmapp.daos.AccountDAO;
import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;

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
        if(verifyValidOpeningBalance(balance)&& verifyValidPin(pin)){
            accountID = accountDAO.openAccount(user.getUserID(),pin,balance);
            if(accountDAO.accountOpen(accountID)){
                return new Account(balance,accountID,pin);
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
}