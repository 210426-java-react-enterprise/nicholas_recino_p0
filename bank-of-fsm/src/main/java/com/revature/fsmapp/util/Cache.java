package com.revature.fsmapp.util;

import com.revature.fsmapp.models.*;
import com.revature.fsmapp.util.collection.List;


public class Cache {

    private AppUser activeUser;
    private Account activeAccount;
    private List<Transaction> transactions;

    public Cache() {

    }

    public AppUser getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(AppUser activeUser) {
        this.activeUser = activeUser;
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(Account activeAccount) {
        this.activeAccount = activeAccount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
