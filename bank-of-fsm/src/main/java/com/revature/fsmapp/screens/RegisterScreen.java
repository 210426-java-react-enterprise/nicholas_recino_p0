package com.revature.fsmapp.screens;

import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {
    private ScreenRouter router;
    public RegisterScreen(BufferedReader consoleReader) {
        super("Register Screen", "/register");
        this.consoleReader = consoleReader;
    }

    @Override
    public void render() {

    }
}
