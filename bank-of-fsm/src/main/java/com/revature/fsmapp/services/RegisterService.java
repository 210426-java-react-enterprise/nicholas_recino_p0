package com.revature.fsmapp.services;

import com.revature.fsmapp.daos.UserDAO;
import com.revature.fsmapp.models.AppUser;

import java.util.regex.Pattern;

public class RegisterService implements Service{
    private final Pattern userNamePattern = Pattern.compile("[A-Za-z0-9_]+");
    private final Pattern passwordPattern = Pattern.compile("[A-Za-z0-9_!@#$%&*]+");
    private final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private final UserDAO userDAO;

    public RegisterService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    private boolean isValidUsername(String username){
        if(username.length()<7)
            return false;
        return(userNamePattern.matcher(username).matches());
    }

    private boolean isValidPassword(String password){
        if(password.length()<7)
            return false;
        return(passwordPattern.matcher(password).matches());
    }

    private boolean isValidEmail(String email) {
        if(email.length()<6)
            return false;
        return(emailPattern.matcher(email).matches());
    }
    public boolean validatePotentialUserInfo(String password, String username,String email){
        AppUser user = null,user1=null;
        boolean validPassword = isValidPassword(password), validUsername = isValidUsername(username), validEmail = isValidEmail(email);
        if(validPassword&&validUsername)
            user = userDAO.findUserByUsernameAndPassword(username,password);
        if(validEmail)
            user1 = userDAO.findUserByEmail(email);

        return ((user==null) && (user1==null) && validUsername && validEmail && validPassword);
    }

    public boolean registerUser(AppUser user){
        return(userDAO.saveUser(user));
    }

}
