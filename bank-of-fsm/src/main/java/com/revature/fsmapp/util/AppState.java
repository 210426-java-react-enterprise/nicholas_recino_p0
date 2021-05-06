package com.revature.fsmapp.util;

import com.revature.fsmapp.daos.UserDAO;
import com.revature.fsmapp.screens.LoginScreen;
import com.revature.fsmapp.screens.RegisterScreen;
import com.revature.fsmapp.screens.Screen;
import com.revature.fsmapp.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

public class AppState {
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private Connection conn;
    private boolean appRunning;

    public AppState(){
        System.out.println("Initializing Application...");
        appRunning = true;
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));

        this.router = new ScreenRouter();
        this.router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader))
                .addScreen(new RegisterScreen(consoleReader));

        final UserDAO userDAO= new UserDAO(conn = ConnectionFactory.getInstance().getConnection());


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

}
