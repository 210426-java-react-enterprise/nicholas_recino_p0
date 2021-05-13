package com.revature.fsmapp.util;

import com.revature.fsmapp.daos.AccountDAO;
import com.revature.fsmapp.daos.UserDAO;
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
    private AccountService accountService;
    private LoginService loginService;
    private RegisterService registerService;
    private Cache cache;

    public AppState(){
        System.out.println("Initializing Application...");
        appRunning = true;
        activeUser = new AppUser();
        userDAO= new UserDAO(conn = ConnectionFactory.getInstance().getConnection());
        accountDAO = new AccountDAO(conn = ConnectionFactory.getInstance().getConnection());
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
        cache = new Cache();
        accountService = new AccountService(accountDAO);
        loginService = new LoginService(userDAO);
        registerService = new RegisterService(userDAO);

        this.router = new ScreenRouter();
        this.router
                .addScreen(new AccountScreen(consoleReader,router,cache,accountService))
                .addScreen(new LoginScreen(consoleReader,router,cache, loginService))
                .addScreen(new RegisterScreen(consoleReader,router,cache,registerService))
                .addScreen(new UserAccountsScreen(consoleReader,router,cache,accountService))
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

    public static void setActiveUser(AppUser user) {
        activeUser = user;
//        this.activeUser.setAccounts(accountDAO.getAccountsByUserID(activeUser));
        System.out.println("Test");
    }

    public Connection getConn() {
        return conn;
    }
}
