package com.revature.fsmapp.screens;

import com.revature.fsmapp.application.Application;
import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.AccountService;
import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.fsmapp.application.Application.app;

public class AccountCreationScreen extends Screen {
    private ScreenRouter router;
    private AccountService accountService;
    private AppUser appUser;

    public AccountCreationScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService){
        super("UserAccountsScreen","/dashboard");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
        appUser = app().getActiveUser();
    }

    @Override
    public void render() {
        System.out.printf("------------------------------------------------\n"+
                "\nHello %s ,what can we do for you today??\n", Application.app().getActiveUser().getFirstName());
        System.out.println("1) Create a brand new account");
        System.out.println("2) Link to an existing account");
        System.out.println("3) Exit application");

        try{
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    //TODO create a new account from account dao and put into activeUser
                    System.out.print("How much would you like to deposit to open an account?"+"\n>");
                    double balance = Double.parseDouble(consoleReader.readLine());
                    System.out.print("Please enter a 4 character pin to use for the account");
                    String pin = consoleReader.readLine();
                    Account accountToAdd = accountService.openAccount(appUser,pin,balance);
                    if(accountToAdd.getAccountId()==-1){
                        System.out.println("System unable to create an account!!!!");
                        break;
                    }
                    appUser.getAccounts().add(accountToAdd);
                    break;
                case "2":
                    //TODO ask for account number or for username and password to select an account from another user
                    // second level dont focus on initially is additional story
                    System.out.println("Navigating to account creation screen");
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