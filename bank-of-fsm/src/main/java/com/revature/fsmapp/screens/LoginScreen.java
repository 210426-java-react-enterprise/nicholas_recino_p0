package com.revature.fsmapp.screens;

import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.LoginService;
import com.revature.fsmapp.util.ScreenRouter;

import static com.revature.fsmapp.application.Application.app;
import java.io.BufferedReader;
import java.io.IOException;

public class LoginScreen extends Screen {
    LoginService service;
    ScreenRouter router;

    public LoginScreen(BufferedReader reader, ScreenRouter router){
        super("LoginScreen","/login");
        consoleReader = reader;
        this.router = router;
        if(app().getService("/login") instanceof  LoginService){
            service = (LoginService) app().getService("/login");
        }else{
            service = null;
        }
    }
    @Override
    public void render() {
        if(service == null){
            System.err.println("Application forcibly closed due to service failure");
            return;
        }
        String username;
        String password;
        AppUser user;
        System.out.println("Welcome to The Bank of the Flying Spaghetti Monster!!!\n" +
                "------------------------------------------------\n"+
                "\n Please Enter Your Information:\n");
        System.out.println("Please Enter Username: ");
        try{
            System.out.println("Please Enter Username: ");
            username = consoleReader.readLine();

            System.out.println("Please Enter Password: ");
            password = consoleReader.readLine();
            user = service.verify(password,username);
            if(user.getUserID() != -1){
                app().setActiveUser(user);
                router.navigate("/dashboard");
            }
        }catch(IOException e){
            // Make Call to Log File to Print Stack Trace
        }

    }
}
