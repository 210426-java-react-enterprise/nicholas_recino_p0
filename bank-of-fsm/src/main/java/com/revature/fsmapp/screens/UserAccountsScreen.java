package com.revature.fsmapp.screens;

import com.revature.fsmapp.application.Application;
import com.revature.fsmapp.services.AccountService;
import com.revature.fsmapp.util.AppState;
import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.fsmapp.application.Application.app;

public class UserAccountsScreen extends Screen {
    private ScreenRouter router;
    private AccountService accountService;
    public UserAccountsScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService){
        super("UserAccountsScreen","/dashboard");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
    }
    @Override
    public void render() {
        System.out.printf("Welcome to The Bank of the Flying Spaghetti Monster!!!\n" +
                "------------------------------------------------\n"+
                "\nHello %s ,what can we do for you today??\n", Application.app().getActiveUser().getFirstName());
        System.out.println("1) View Account(s)");
        System.out.println("2) Create an Account");
        System.out.println("3) Exit application");

        try{
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to account selection screen");
                    router.navigate("/acccount_selection");
                    break;
                case "2":
                    System.out.println("Navigating to account creation screen");
                    router.navigate("/account_creation");
                    break;
                case "3":
                    System.out.println("Exiting application!");
                    app().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid selection, Please Try Again!!!");
                    Thread.sleep(1000);
                    render();
            }
        }catch(IOException | InterruptedException e){
            // Make Call to Log File to Print Stack Trace
            e.printStackTrace();
        }
    }
}
