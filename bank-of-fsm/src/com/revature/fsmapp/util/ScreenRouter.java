package com.revature.fsmapp.util;

import com.revature.fsmapp.screens.Screen;
import com.revature.fsmapp.util.collection.ArrayList;

public class ScreenRouter {
    ArrayList<Screen> screens;

    public ScreenRouter(Screen... screenDisplays){
        for(Screen screen:screenDisplays){
            screens.add(screen);
        }
    }
}
