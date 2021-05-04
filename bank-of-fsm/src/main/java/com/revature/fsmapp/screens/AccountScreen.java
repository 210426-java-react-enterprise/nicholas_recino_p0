package com.revature.fsmapp.screens;

import java.io.BufferedReader;

public class AccountScreen extends Screen {

    public AccountScreen(BufferedReader consoleReader) {
        super("AccountScreen","/Account");
        this.consoleReader = consoleReader;

    }

    @Override
    public void render() {

    }
}
