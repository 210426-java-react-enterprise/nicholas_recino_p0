package com.revature.fsmapp.screens;

import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.AccountService;
import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;
import static com.revature.fsmapp.application.Application.app;

public class AccountScreen extends Screen {
    AppUser user;
    Account activeAccount;
    AccountService accountService;
    ScreenRouter router;
    int activeAccountNum;
    public AccountScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService) {
        super("AccountScreen","/account_info");
        this.consoleReader = consoleReader;
        user = app().getActiveUser();
        activeAccountNum = user.getActiveAccountNum();
        this.accountService = accountService;
        this.router = router;
        activeAccount = user.getActiveAccount();
    }

    @Override
    public void render() {

    }
}
