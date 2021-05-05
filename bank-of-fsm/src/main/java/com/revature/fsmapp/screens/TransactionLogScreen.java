package com.revature.fsmapp.screens;

import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;

public class TransactionLogScreen extends Screen
{
    private ScreenRouter router;

    public TransactionLogScreen(BufferedReader consoleReader,ScreenRouter router) {
        super("TransactionLog", "/account_log");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

    }
}
