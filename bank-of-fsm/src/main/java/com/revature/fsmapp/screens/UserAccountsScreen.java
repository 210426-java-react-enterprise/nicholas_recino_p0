package com.revature.fsmapp.screens;

import com.revature.fsmapp.application.Application;
import com.revature.fsmapp.exceptions.AccountNotFoundException;
import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.AccountService;
import com.revature.fsmapp.util.AppState;
import com.revature.fsmapp.util.Cache;
import com.revature.fsmapp.util.ScreenRouter;
import com.revature.fsmapp.util.collection.List;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.fsmapp.application.Application.app;

public class UserAccountsScreen extends Screen {
    private ScreenRouter router;
    private AccountService accountService;
    private Cache cache;
    public UserAccountsScreen(BufferedReader consoleReader, ScreenRouter router, Cache cache, AccountService accountService){
        super("UserAccountsScreen","/dashboard");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
        this.cache = cache;
    }
    @Override
    public void render() {
        AppUser activeUser = cache.getActiveUser();
        List<Account> accounts;
        System.out.printf("User Dashboard\n" +
                "------------------------------------------------\n"+
                "\nHello %s ,what can we do for you today??\n", activeUser.getFirstName());
        System.out.println("1) View Account");
        System.out.println("2) Create an Account");
        System.out.println("3) Exit application");

        try{
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    accounts = accountService.getUserAccounts(activeUser);
                    Account accountSelection;
                    if(accounts.size()==0){
                        System.out.println("You do not have any open and registered accounts on file");
                        break;
                    }
                    for(Account account:accounts) {
                        System.out.printf("---------------------------\nAccount ID: %d\n", account.getAccountNumber());
                        System.out.printf("Current Balance: $%.2f\n---------------------------\n", account.getBalance());
                    }
                        try{
                            System.out.print("Please enter the account ID you wish to select: ");
                            int accountNumToGet = Integer.parseInt(consoleReader.readLine());
                            accountSelection = accountService.validateAccount(accounts,accountNumToGet);
                            cache.setActiveAccount(accountSelection);
                        }catch(AccountNotFoundException e){
                            router.navigate("/dashboard");
                            break;
                        }
                        router.navigate("/account_info");
                        break;


                case "2":
                    System.out.print("How much would you like to deposit to open an account?"+"\n>");
                    double balance = Double.parseDouble(consoleReader.readLine());
                    System.out.print("Please enter a 4 character pin to use for the account: ");
                    String pin = consoleReader.readLine();
                    Account accountToAdd = accountService.openAccount(cache.getActiveUser(),pin,balance);
                    if(accountToAdd.getAccountId()==-1){
                        System.out.println("System unable to create an account!!!!");
                    }
                    accounts = activeUser.getAccounts();
                    accounts.add(accountToAdd);
                    activeUser.setAccounts(accounts);
                    activeUser.setActiveAccount(accountToAdd);
                    cache.setActiveAccount(accountToAdd);
                    router.navigate("/account_info");
                    break;
//                case "3":
//                    //TODO ask for account number or for username and password to select an account from another user
//                    // second level dont focus on initially is additional story
//                    System.out.println("Enter in the Account Number you would like to link to your User Account");
//                    int accountNumber = Integer.parseInt(consoleReader.readLine());
//                    if(!accountService.linkAccount(AppState.getActiveUser(),accountNumber)){
//                        System.out.printf("Account # %d is not a valid account",accountNumber);
//                    }
//                    render();
//                    break;
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
        }
    }
}
