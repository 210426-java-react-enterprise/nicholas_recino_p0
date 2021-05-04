package com.revature.fsmapp.screens;

import java.io.BufferedReader;

public class TransactionScreen extends Screen
{
    public TransactionScreen(BufferedReader consoleReader) {
        super("Transactions", "/transaction");
        this.consoleReader = consoleReader;
    }

    @Override
    public void render() {

    }
}
