package com.revature.fsmapp.models;

import com.revature.fsmapp.util.collection.ArrayList;
import com.revature.fsmapp.util.collection.List;

public class AppUser {
    private String userName;
    private String userEmail;
    private String password;
    private String phoneNumber;
    private ArrayList accounts;

    public AppUser(){
        accounts = new ArrayList(1);
        accounts.add(new Account());
    }

    public String[] getUserInfo(){
        String[] userInfo = new String[accounts.size()+4];
        userInfo[0] = userName;
        userInfo[1] = userEmail;
        userInfo[2] = password;
        userInfo[3] = phoneNumber;
        String accountNum;
        for(int i=4;i<userInfo.length;i++){
//            accountNum = account;
            userInfo[i] = "";
        }
        return userInfo;
    }



}
