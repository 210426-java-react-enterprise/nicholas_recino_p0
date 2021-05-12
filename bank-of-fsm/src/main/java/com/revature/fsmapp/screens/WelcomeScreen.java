package com.revature.fsmapp.screens;

import com.revature.fsmapp.util.ScreenRouter;
import static com.revature.fsmapp.application.Application.app;
import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen extends Screen{
    private final ScreenRouter router;

    public WelcomeScreen(BufferedReader reader, ScreenRouter router){
        super("WelcomeScreen","/welcome");
        consoleReader = reader;
        this.router = router;
    }
    @Override
    public void render() {
        System.out.println("Welcome to The Bank of the Flying Spaghetti Monster!!!\n" +
                "------------------------------------------------\n"+
                "\n What task can we do for you today\n");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit application");

        try{
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to login screen");
                    router.navigate("/login");
                    break;
                case "2":
                    System.out.println("Navigating to register screen");
                    router.navigate("/register");
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
