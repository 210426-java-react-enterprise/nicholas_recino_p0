package com.revature.fsmapp.application;

import com.revature.fsmapp.util.AppState;

public class Application {

    private static AppState app = new AppState();

    public static void main(String[] args) {
        while(app.isAppRunning()){
            app.getRouter().navigate("/home");
        }

    }

    public static AppState app(){
        return app;
    }

}
