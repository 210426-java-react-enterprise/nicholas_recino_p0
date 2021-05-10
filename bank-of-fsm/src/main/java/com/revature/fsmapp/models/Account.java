package com.revature.fsmapp.models;

import com.revature.fsmapp.util.collection.ArrayList;
import com.revature.fsmapp.util.collection.DoublyLinkedList;
import com.revature.fsmapp.util.collection.List;

// TODO Refactor String password to allow for proper User checking as an account can have more than one user
public class Account {
    private int accountId;
    private int accountNumber;
    private String pin;
    private double balance;
    private List<Transaction> transactionLog;

    public Account(double balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.pin = "password";
        transactionLog = new ArrayList<>(100);
    }

    public Account(double balance, int accountNumber,String pin) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    // TODO implement default table of account numbers to apply a new account number to, maybe check to see if its in use first
    public Account() {
        accountNumber = 00000001;
        balance = 0;
        pin = "password";
    }

    public Account(String password) {
        accountNumber = 00000001;
        balance = 0;
        pin = password;
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId,String pin) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(List<Transaction> transactionLog) {
        this.transactionLog = transactionLog;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    //  Verifies Account Password prior to changing account number
    public void setAccountNumber(int accountNumber,String password) {
        if(this.pin.equals(password)) {
            this.accountNumber = accountNumber;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }
//  Verifies Account Password prior to changing balance
    public void setBalance(double balance,String password) {
        if(this.pin.equals(password)) {
            this.balance = balance;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }
//  Verifies that Current Password is accurate prior to changing password.
    public void setPin(String currentPassword,String newPassword) {
        if(this.pin.equals(currentPassword)) {
            this.pin = newPassword;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }
}
