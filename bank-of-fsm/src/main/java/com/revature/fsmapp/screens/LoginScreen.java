package com.revature.fsmapp.screens;

import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.LoginService;
import com.revature.fsmapp.util.AppState;
import com.revature.fsmapp.util.Cache;
import com.revature.fsmapp.util.ScreenRouter;

import static com.revature.fsmapp.application.Application.app;
import java.io.BufferedReader;
import java.io.IOException;

public class LoginScreen extends Screen {
    private LoginService service;
    private ScreenRouter router;
    private Cache cache;

    public LoginScreen(BufferedReader reader, ScreenRouter router,Cache cache, LoginService service){
        super("LoginScreen","/login");
        consoleReader = reader;
        this.router = router;
        this.service = service;
        this.cache = cache;
    }
    @Override
    public void render() {
        String username;
        String password;
        AppUser user;
        System.out.println("\nWelcome to The Bank of the Flying Spaghetti Monster!!!\n" +
                "------------------------------------------------\n"+
                "\n Please Enter Your Information:\n");
        try{
            System.out.print("Please Enter Username: ");
            username = consoleReader.readLine();

            System.out.print("Please Enter Password: ");
            password = consoleReader.readLine();
            user = service.verify(password,username);
            if(user.getUserID() != -1){
                cache.setActiveUser(user);
                router.navigate("/dashboard");
            }else{
                System.err.println("Incorrect Username or Password Given Please Try Again!!!\n\n");
            }
        }catch(IOException e){
            // Make Call to Log File to Print Stack Trace
        }

    }
}
