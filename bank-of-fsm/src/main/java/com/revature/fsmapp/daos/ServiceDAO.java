package com.revature.fsmapp.daos;

import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.Service;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;

public class ServiceDAO {
    private static JSONObject jsonObj;

    public ServiceDAO(JSONObject jsonObj){
        this.jsonObj = jsonObj;
    }
    public void saveUser(Service user, BufferedWriter writer) throws IOException {
            jsonObj.writeJSONString(writer);
    }
}
