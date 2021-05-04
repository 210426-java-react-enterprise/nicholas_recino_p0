package com.revature.fsmapp.screens;

import java.io.BufferedReader;

public class UserAccountsScreen extends Screen {
    public UserAccountsScreen(BufferedReader consoleReader){
        super("UserAccountsScreen","/dashboard");
        this.consoleReader = consoleReader;
    }
    @Override
    public void render() {

    }
}
