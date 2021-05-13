package com.revature.fsmapp.application;

import com.revature.fsmapp.util.AppState;

public class Application {

    private static AppState app = new AppState();

    public static void main(String[] args) {
        app().getRouter().navigate("/welcome");
        while(app.isAppRunning()){
            app.getRouter().getCurrentScreen().render();
        }

    }

    public static AppState app(){
        return app;
    }

}
