package com.revature.fsmapp.screens;

import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen extends Screen{
    public WelcomeScreen(BufferedReader reader){
        super("WelcomeScreen","/home");
        consoleReader = reader;
    }
    @Override
    public void render() {
        String username;
        String password;
        System.out.println("Welcome to The Bank of the Flying Spaghetti Monster!!!\n" +
                "------------------------------------------------\n"+
                "\n What task can we do for you today\n");

        try{
            System.out.println("Please Enter Username: ");
            username = consoleReader.readLine();
        }catch(IOException e){
            // Make Call to Log File to Print Stack Trace
        }

    }
}
