package com.revature.fsmapp.util;

import com.revature.fsmapp.exceptions.InvalidRouteException;
import com.revature.fsmapp.screens.Screen;
import com.revature.fsmapp.util.collection.ArrayList;

import java.util.Optional;

public class ScreenRouter {
    private ArrayList<Screen> screens;
    private Optional<Screen> currentScreen;

    public ScreenRouter(){
        screens = new ArrayList<>();
    }

    public ScreenRouter addScreen(Screen screen){
        screens.add(screen);
        return this;
    }
    // Navigates to specific String
    public void navigate(String route){
        currentScreen = screens
                .stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst();
    }

    public Screen getCurrentScreen(){
        return currentScreen.orElseGet(() -> screens.stream().filter(screen -> screen.getRoute().equals("/welcome")).findFirst().get());
    }


}
