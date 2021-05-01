package com.revature.fsmapp.models;

import com.revature.fsmapp.util.collection.DoublyLinkedList;

// TODO Refactor String password to allow for proper User checking as an account can have more than one user
public class Account {
    private double balance;
    private int accountNumber;
    private String password;
    private DoublyLinkedList transactionLog;

    public Account(double balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.password = "password";
        transactionLog = new DoublyLinkedList();
    }

    public Account(double balance, int accountNumber,String password) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.password = password;
    }
// TODO implement default table of account numbers to apply a new account number to, maybe check to see if its in use first
    public Account() {
        accountNumber = 00000001;
        balance = 0;
        password = "password";
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
//  Verifies Account Password prior to changing balance
    public void setBalance(double balance,String password) {
        if(this.password.equals(password)) {
            this.balance = balance;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }
//  Verifies Account Password prior to changing account number
   public void setAccountNumber(int accountNumber,String password) {
        if(this.password.equals(password)) {
            this.accountNumber = accountNumber;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }

//  Verifies that Current Password is accurate prior to changing password.
    public void setPassword(String currentPassword,String newPassword) {
        if(this.password.equals(currentPassword)) {
            this.password = newPassword;
        }else{
            System.err.println("Proper credentials required, please re-enter password!!!!");
        }
    }
}
