package com.revature.fsmapp.screens;

import java.io.BufferedReader;

public abstract class Screen {
     protected BufferedReader consoleReader;
     protected String name;
     protected  String route;

     public String getName() {
          return name;
     }

     public String getRoute() {
          return route;
     }

     public Screen(String name, String route){
          this.name = name;
          this.route = route;
     }
     public abstract void render();



}
