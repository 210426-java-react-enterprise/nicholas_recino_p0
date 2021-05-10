package com.revature.fsmapp.services;

import com.revature.fsmapp.daos.UserDAO;
import com.revature.fsmapp.models.AppUser;

public class UserService implements Service {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void register(AppUser newUser){

    }




    public boolean userValidation(AppUser user){
        if(user == null)
            return false;
        return !user.getUserName().trim().isEmpty();
    }

    public void init(){

    }
}
