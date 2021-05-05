package com.revature.fsmapp.screens;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginScreen extends Screen {

    public LoginScreen(BufferedReader reader){
        super("LoginScreen","/login");
        consoleReader = reader;
    }
    @Override
    public void render() {
        String username;
        String password;
        System.out.println("Welcome to The Bank of the Flying Spaghetti Monster!!!\n" +
                "------------------------------------------------\n"+
                "\n Please Enter Your Information:\n");
        System.out.println("Please Enter Username: ");
        try{
            System.out.println("Please Enter Username: ");
            username = consoleReader.readLine();
        }catch(IOException e){
            // Make Call to Log File to Print Stack Trace
        }

    }
}
