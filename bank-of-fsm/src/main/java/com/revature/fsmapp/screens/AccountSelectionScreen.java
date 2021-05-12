package com.revature.fsmapp.screens;

import com.revature.fsmapp.application.Application;
import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.AccountService;
import com.revature.fsmapp.util.ScreenRouter;
import com.revature.fsmapp.util.collection.List;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.fsmapp.application.Application.app;

public class AccountSelectionScreen extends Screen {
    private ScreenRouter router;
    private AccountService accountService;
    private AppUser appUser;
    private List<Account> accounts;

    public AccountSelectionScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService){
        super("AccountSelectionScreen","/account_selection");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
        appUser = app().getActiveUser();
        accounts = appUser.getAccounts();
    }

    @Override
    public void render() {
        System.out.printf("------------------------------------------------\n"+
                "\nHello %s ,what can we do for you today??\n", Application.app().getActiveUser().getFirstName());
        System.out.printf("1) Go to Active Account #%d \n",appUser.getActiveAccountNum());
        System.out.println("2) Select Account to be active");
        System.out.println("3) Exit application");

        try{
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "2":
                    appUser.setActiveAccountNum(selectActiveAccount());
                case "1":
                    router.navigate("/account_info");
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
        if(app().isAppRunning()){
            router.navigate("/dashboard");
        }
    }

    private int selectActiveAccount()throws IOException{
        int selection;
        int accountsSize = accounts.size();
        System.out.printf("Please enter which account you would like to select from 1-%d \n",accountsSize);
        for(int i=0;i<accountsSize;i++){
            System.out.println(i+".) #"+accounts.get(i).getAccountId());
        }
        selection = consoleReader.read();
        selection-=1;
        if(selection<0 || selection>accountsSize){
            selectActiveAccount();
        }
        return accounts.get(selection).getAccountId();
    }
}