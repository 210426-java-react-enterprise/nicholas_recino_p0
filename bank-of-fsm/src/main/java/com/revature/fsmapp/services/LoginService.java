package com.revature.fsmapp.services;

import com.revature.fsmapp.daos.UserDAO;
import com.revature.fsmapp.models.AppUser;

import java.util.regex.Pattern;

public class LoginService implements Service{
    final Pattern userNamePattern = Pattern.compile("[A-Za-z0-9_]+");
    final Pattern passwordPattern = Pattern.compile("[A-Za-z0-9_!@#$%&*]+");
    private final UserDAO userDAO;

    public LoginService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    private boolean isValidUsername(String username){
        if(username.length()<8)
            return false;
        return(userNamePattern.matcher(username).matches());
    }

     private boolean isValidPassword(String password){
        if(password.length()<8)
            return false;
        return(passwordPattern.matcher(password).matches());
     }

     public void init(){

     }

     public AppUser verify(String password, String username){
            AppUser user=null;
            if(isValidPassword(password)&&isValidUsername(username)){
                user = userDAO.findUserByUsernameAndPassword(username,password);
            }
            return (user == null) ? new AppUser() : user;
     }
}
