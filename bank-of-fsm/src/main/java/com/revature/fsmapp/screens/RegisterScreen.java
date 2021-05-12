package com.revature.fsmapp.screens;

import com.revature.fsmapp.application.Application;
import com.revature.fsmapp.exceptions.DuplicateAccountException;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.RegisterService;
import com.revature.fsmapp.services.UserService;
import com.revature.fsmapp.util.AppState;
import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.fsmapp.application.Application.app;

public class RegisterScreen extends Screen {
    private final ScreenRouter router;
    private  final RegisterService registerService;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, RegisterService service) {
        super("Register Screen", "/register");
        this.consoleReader = consoleReader;
        this.registerService = service;
        this.router = router;
    }

    /**
     * Presents the user with dialogue and performs various functions based on user input.
     */
    @Override
    public void render() {
        try {
            AppUser user = new AppUser();
            String username= "" ,password= "" ,firstName= "" ,lastName = "",email = "";
            int age = 0;
            System.out.println("In order to register for an account please enter in your personal information below:");

            System.out.print("Please Enter Username: ");
            username = consoleReader.readLine();

            System.out.print("Please Enter Password: ");
            password = consoleReader.readLine();

            System.out.print("Please Enter Your Email: ");
            email = consoleReader.readLine();

            if(!registerService.validatePotentialUserInfo(password,username,email)){
                throw new DuplicateAccountException();
            }

            System.out.print("Please Enter Your First Name: ");
            firstName = consoleReader.readLine();
            System.out.print("Please Enter Your Last Name: ");
            lastName = consoleReader.readLine();
            System.out.print("Please Enter Your Age, note you must be 13 or older to create an account: ");
            age = Integer.parseInt(consoleReader.readLine());

            user.setUserName(username);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserEmail(email);
            user.setAge(age);

            if(registerService.registerUser(user)){
                AppState.setActiveUser(user);
                System.out.println("Navigating to the Account Creation Screen");
                router.navigate("/account_creation");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Registration failed due to invalid information");
            render();
        }catch(DuplicateAccountException e){
            e.printStackTrace();
            System.err.println("Information already taken, please try again");
            render();
        }

    }
}
