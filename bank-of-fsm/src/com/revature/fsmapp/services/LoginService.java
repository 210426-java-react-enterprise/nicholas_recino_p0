package com.revature.fsmapp.services;

import java.util.regex.Pattern;

public class LoginService implements Service{
    final Pattern userNamePattern = Pattern.compile("[A-Za-z0-9_]+");
    final Pattern passwordPattern = Pattern.compile("[A-Za-z0-9_!@#$%&*]+");

    public boolean isValidUsername(String username){
        if(username.length()<8)
            return false;
        return(userNamePattern.matcher(username).matches());
    }

     public boolean isValidPassword(String password){
        if(password.length()<8)
            return false;
        return(passwordPattern.matcher(password).matches());
     }

     public void init(){

     }
}
