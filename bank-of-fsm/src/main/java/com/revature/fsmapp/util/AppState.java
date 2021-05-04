package com.revature.fsmapp.util;

import com.revature.fsmapp.daos.UserDAO;
import com.revature.fsmapp.screens.Screen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState(){
        System.out.println("Initializing Application...");
        appRunning = true;
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));

        this.router = new ScreenRouter();

        final UserDAO userDAO= new UserDAO();


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
