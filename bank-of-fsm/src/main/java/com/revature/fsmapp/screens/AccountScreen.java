package com.revature.fsmapp.screens;

import java.io.BufferedReader;

public class AccountScreen extends Screen {

    public AccountScreen(BufferedReader consoleReader) {
        super("AccountScreen","/account_info");
        this.consoleReader = consoleReader;

    }

    @Override
    public void render() {

    }
}
