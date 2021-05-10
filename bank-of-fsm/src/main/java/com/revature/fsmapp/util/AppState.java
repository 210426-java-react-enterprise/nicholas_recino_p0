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
    private AppUser activeUser;
    private ServiceHandler services;
    private boolean appRunning;
    private final UserDAO userDAO;
    private final AccountDAO accountDAO;
    private int attemptsLeft = 0;

    public AppState(){
        System.out.println("Initializing Application...");
        appRunning = true;
        userDAO= new UserDAO(conn = ConnectionFactory.getInstance().getConnection());
        accountDAO = new AccountDAO(conn = ConnectionFactory.getInstance().getConnection());
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));

        this.router = new ScreenRouter();
        // Refactor to use Service handler instead of a  new instance
        this.router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader,router))
                .addScreen(new RegisterScreen(consoleReader,new UserService(userDAO),router))
                .addScreen(new UserAccountsScreen(consoleReader,router,new AccountService(accountDAO)));



        services = initServices(userDAO);


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

    public Service getService(String serviceName){
        Service service = null;
        try {
            service = services.startService(serviceName);
            attemptsLeft = 0;
            return service;
        } catch (ServiceNotFoundException e) {
            attemptsLeft++;
            e.printStackTrace();
            services = null;
            services = initServices(userDAO);
        }
        if(attemptsLeft<4)
            service = getService(serviceName);
        appRunning = false;
        return service;
    }

    private ServiceHandler initServices(UserDAO userDAO) {
        return ServiceHandler.getInstance()
                .addService(new LoginService(userDAO), "/login")
                .addService(new RegisterService(userDAO), "/register");
    }

    public AppUser getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(AppUser activeUser) {
        this.activeUser = activeUser;
        this.activeUser.setAccounts(accountDAO.getAccountsByUserID(activeUser));
    }

    public Connection getConn() {
        return conn;
    }
}
