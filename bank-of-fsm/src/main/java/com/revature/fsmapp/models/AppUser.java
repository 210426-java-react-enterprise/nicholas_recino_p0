package com.revature.fsmapp.models;

import com.revature.fsmapp.util.collection.ArrayList;
import com.revature.fsmapp.util.collection.List;

public class AppUser {
    private int userID;
    private String userName;
    private String userEmail;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private int age;

    private List<Account> accounts;
    private int activeAccountNum;

    public AppUser(){
        accounts = new ArrayList();
        accounts.add(new Account());
        activeAccountNum = 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("username='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(userEmail).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getActiveAccountNum() {
        return activeAccountNum;
    }

    public void setActiveAccountNum(int activeAccountNum) {
        this.activeAccountNum = activeAccountNum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList accounts) {
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
