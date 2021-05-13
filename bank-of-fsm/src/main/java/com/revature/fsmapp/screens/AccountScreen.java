package com.revature.fsmapp.screens;

import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.models.Transaction;
import com.revature.fsmapp.services.AccountService;
import com.revature.fsmapp.util.Cache;
import com.revature.fsmapp.util.ScreenRouter;
import com.revature.fsmapp.util.collection.List;


import javax.naming.InsufficientResourcesException;
import java.io.BufferedReader;
import java.io.IOException;


public class AccountScreen extends Screen {
    private AccountService accountService;
    private ScreenRouter router;
    private Cache cache;

    public AccountScreen(BufferedReader consoleReader, ScreenRouter router, Cache cache, AccountService accountService) {
        super("AccountScreen","/account_info");
        this.consoleReader = consoleReader;
        this.cache = cache;
        this.accountService = accountService;
        this.router = router;
    }

    @Override
    public void render() {

        Account activeAccount = cache.getActiveAccount();
        AppUser activeUser = cache.getActiveUser();
        Transaction transaction;

        System.out.println("-------------------------------------------------");
        System.out.printf("Current Account: %s\n", activeAccount.getAccountNumber());
        System.out.printf("Current Balance: $%.2f\n", activeAccount.getBalance());
        System.out.println("Options available");
        System.out.println("1) Deposit funds");
        System.out.println("2) Withdraw funds");
        System.out.println("3) Back to Account Selection");
        try {
            System.out.println("Please Make A Selection: ");
            String choice = consoleReader.readLine();
            double amount = 0.0;

            switch (choice) {
                case "1":
                    System.out.print("Please enter an amount to deposit:\n> ");
                    amount = Double.parseDouble(consoleReader.readLine());
                    accountService.addBalance(amount, activeAccount.getAccountId(),activeAccount);

//                    transaction = accountService.recordTransaction(activeUser.getUserName(), activeAccount.getAccountId(), activeAccount.getAccountId(), amount);
//                    cache.getTransactions().add(transaction);
                    accountService.addBalance(amount,activeAccount.getAccountId(),activeAccount);
                    System.out.printf("Your new balance is: $%.2f\n", activeAccount.getBalance());
                    router.navigate("/account");
                    break;
                case "2":
                    System.out.print("Please enter an amount to withdraw:\n> ");
                    amount = Double.parseDouble(consoleReader.readLine());
                    accountService.subtractBalance(amount, activeAccount.getAccountId(),activeAccount);

//                    transaction = accountService.recordTransaction(activeUser.getUserName(), activeAccount.getAccountId(), activeAccount.getAccountId(), amount);
//                    cache.getTransactions().add(transaction);
                    System.out.printf("Your new balance is: $%.2f\n", activeAccount.getBalance());
                    router.navigate("/account");
                    break;
//                case "3":
//                    System.out.print("Please enter an amount to transfer:\n> ");
//                    amount = Double.parseDouble(consoleReader.readLine());
//                    System.out.print("Please enter the accounts id to transfer to:\n> ");
//                    int recipient = Integer.parseInt(consoleReader.readLine());
//
//
//                    if (accountService.accountExists(recipient)) {
//                        accountService.subtractBalance(amount, activeAccount.getAccountId(),activeAccount);
//                        accountService.addBalance(amount, recipient,activeAccount);
//                        activeAccount.setBalance(activeAccount.getBalance() - amount);
//                        transaction = accountService.recordTransaction(activeUser.getUserName(), activeAccount.getAccountId(), recipient,amount);
//                        cache.getTransactions().add(transaction);
//                        System.out.println("Transaction successful!!!");
//                    } else {
//                        System.out.println("Transaction failed. Valid account not specified");
//                        router.navigate("/account_info");
//                    }
//                    break;
//                case "4":
//                    List<Transaction> transactions = cache.getTransactions();
//
//                    if(transactions == null) {
//                        System.out.println("This account has no transaction history.");
//                    } else {
//                        for(Transaction transactionDisplay: transactions) {
//                            System.out.println(transactionDisplay);
//                        }
//                    }
//                    router.navigate("/account_info");
//                    break;
                case "3":
                    router.navigate("/dashboard");
                    break;
                default:
                    System.out.println("Your choice was invalid, please try again!!!");

            }


        } catch (IOException e) {

        }catch (InsufficientResourcesException e){
            System.err.println("Not enough funds available to make the transaction!!!");
        }
    }
}
