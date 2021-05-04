package com.revature.fsmapp.util;

import com.revature.fsmapp.screens.Screen;
import com.revature.fsmapp.util.collection.ArrayList;

public class ScreenRouter {
    private ArrayList<Screen> screens;

    public ScreenRouter(){

    }

    public ScreenRouter addScreen(Screen screen){
        screens.add(screen);
        return this;
    }
    // Navigates to specific String
    public void navigate(String route){
        for(int i=0; i< screens.size();i++){
            Screen screen = screens.get(i);
            if(screen.getRoute().equals(route)){
                screen.render();
            }
        }
    }


}
