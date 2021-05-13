package com.revature.fsmapp.models;

import java.time.LocalDateTime;

public class Transaction {

    private int recipientAccount;
    private int senderAccount;
    private String recipient;
    private String sender;

    private int transactionID;
    private double amount;
    private LocalDateTime transactionDate;

    public Transaction(){

    }

    public int getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(int recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public int getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(int senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("transactionID=").append(transactionID);
        sb.append(", sender='").append(sender).append('\'');
        sb.append(", senderAccount=").append(senderAccount);
        sb.append(", recipient='").append(recipient).append('\'');
        sb.append(", recipientAccount=").append(recipientAccount);
        sb.append(", amount=").append(amount);
        sb.append(", date=").append(transactionDate);
        sb.append('}');
        return sb.toString();
    }
}
