package com.revature.fsmapp.screens;

import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.UserService;
import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {
    private ScreenRouter router;
    private  final UserService userService;
    private ScreenRouter screenRouter;
    public RegisterScreen(BufferedReader consoleReader, UserService service, ScreenRouter screenRouter) {
        super("Register Screen", "/register");
        this.consoleReader = consoleReader;
        this.userService = service;
        this.screenRouter = screenRouter;
    }

    /**
     * Presents the user with dialogue and performs various functions based on user input.
     */
    @Override
    public void render() {
        try {
            System.out.println("In order to register for an account please enter in your personal information below:");

            AppUser user = new AppUser();

            //Logic to record info from console

            if(userService.userValidation(user))
                userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Registration failed due to invalid information");
            render();
        }
        screenRouter.navigate("/dashboard");
    }
}
