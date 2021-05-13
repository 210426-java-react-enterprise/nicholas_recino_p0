package com.revature.fsmapp.util;

import org.json.simple.JSONObject;

import java.io.PrintWriter;

public class Logger {
    private static JSONObject jsonObj;

    public Logger(){
        jsonObj = new JSONObject();
    }
    
// TODO implement Logging
    public static void logError(Exception e){
        e.getMessage();

    }
// TODO implement Logging
    public static void logMessages(String message){

    }
}
