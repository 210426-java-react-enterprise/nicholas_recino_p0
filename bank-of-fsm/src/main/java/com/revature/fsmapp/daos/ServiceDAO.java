package com.revature.fsmapp.daos;

import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.services.Service;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO {
    private Connection conn;

    public ServiceDAO(Connection conn){
        this.conn = conn;
    }

}
