package com.revature.fsmapp.util;

import com.revature.fsmapp.daos.AccountDAO;
import com.revature.fsmapp.daos.UserDAO;
import com.revature.fsmapp.exceptions.ServiceNotFoundException;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.screens.*;
import com.revature.fsmapp.services.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

public class AppState {
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private Connection conn;
    private static AppUser activeUser;
    private boolean appRunning;
    private  UserDAO userDAO;
    private  AccountDAO accountDAO;
    private int attemptsLeft = 0;

    public AppState(){
        System.out.println("Initializing Application...");
        appRunning = true;
        activeUser = new AppUser();
        userDAO= new UserDAO(conn = ConnectionFactory.getInstance().getConnection());
        accountDAO = new AccountDAO(conn = ConnectionFactory.getInstance().getConnection());
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));

        this.router = new ScreenRouter();
        // Refactor to use Service handler instead of a  new instance
        this.router.addScreen(new AccountCreationScreen(consoleReader,router,new AccountService(accountDAO)))
                .addScreen(new AccountScreen(consoleReader,router,new AccountService(accountDAO)))
                .addScreen(new AccountSelectionScreen(consoleReader,router,new AccountService(accountDAO)))
                .addScreen(new LoginScreen(consoleReader,router,new LoginService(userDAO)))
                .addScreen(new RegisterScreen(consoleReader,router,new RegisterService(userDAO)))
                .addScreen(new TransactionLogScreen(consoleReader,router))
                .addScreen(new TransactionScreen(consoleReader))
                .addScreen(new WelcomeScreen(consoleReader,router));

        System.out.println("Application Initialized...");
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public ScreenRouter getRouter(){
        return router;
    }

    public void setAppRunning(boolean appRunning){
        this.appRunning = appRunning;
    }

    public static AppUser getActiveUser() {
        if(activeUser==null)
            return new AppUser();
        return activeUser;
    }

    public void setActiveUser(AppUser activeUser) {
        this.activeUser = activeUser;
//        this.activeUser.setAccounts(accountDAO.getAccountsByUserID(activeUser));
    }

    public Connection getConn() {
        return conn;
    }
}
