package com.revature.fsmapp.screens;

import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {
    private ScreenRouter router;
    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("Register Screen", "/register");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

    }
}
