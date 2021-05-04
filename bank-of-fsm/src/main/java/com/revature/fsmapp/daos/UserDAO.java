package com.revature.fsmapp.daos;

import com.revature.fsmapp.models.AppUser;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;

public class UserDAO {
    private static JSONObject jsonObj = new JSONObject();

    public UserDAO(){
    }
//  Uses writer pass through from application to keep from collisions when accessing the users file
//  Then encodes user into json string to be written to file;
    public void saveUser(AppUser user, BufferedWriter writer) throws IOException{
        for(String o:user.getUserInfo())
        jsonObj.writeJSONString(writer);
    }
}
