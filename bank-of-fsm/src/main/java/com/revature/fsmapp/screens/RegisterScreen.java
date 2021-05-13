package com.revature.fsmapp.screens;

import com.revature.fsmapp.exceptions.DuplicateAccountException;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.RegisterService;
import com.revature.fsmapp.util.Cache;
import com.revature.fsmapp.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class RegisterScreen extends Screen {
    private  ScreenRouter router;
    private  RegisterService registerService;
    private Cache cache;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router,Cache cache, RegisterService service) {
        super("Register Screen", "/register");
        this.consoleReader = consoleReader;
        this.registerService = service;
        this.router = router;
        this.cache = cache;
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

            System.out.print("Please Enter Username (7 Characters Minimum): ");
            username = consoleReader.readLine();

            System.out.print("Please Enter Password: (7 Characters Minimum): ");
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
                cache.setActiveUser(user);
                System.out.println("Navigating to the Account Creation Screen");
                router.navigate("/account_creation");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Registration failed due to invalid information");
            render();
        }catch(DuplicateAccountException e){
            System.err.println("Information already taken or not valid, please try again");
            render();
        }

    }
}
