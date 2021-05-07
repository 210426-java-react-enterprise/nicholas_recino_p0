package com.revature.fsmapp.models;

import com.revature.fsmapp.util.collection.ArrayList;
import com.revature.fsmapp.util.collection.DoublyLinkedList;

// TODO Refactor String password to allow for proper User checking as an account can have more than one user
public class Account {
    private int accountId;
    private int accountNumber;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactionLog;

    public Account(double balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.pin = "password";
        transactionLog = new ArrayList<>();
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

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
//  Verifies Account Password prior to changing balance
    public void setBalance(double balance,String password) {
        if(this.pin.equals(password)) {
            this.balance = balance;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }
//  Verifies Account Password prior to changing account number
   public void setAccountNumber(int accountNumber,String password) {
        if(this.pin.equals(password)) {
            this.accountNumber = accountNumber;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }

//  Verifies that Current Password is accurate prior to changing password.
    public void setPassword(String currentPassword,String newPassword) {
        if(this.pin.equals(currentPassword)) {
            this.pin = newPassword;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }
}