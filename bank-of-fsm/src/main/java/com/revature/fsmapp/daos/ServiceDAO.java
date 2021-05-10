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

    public void saveUser(AppUser user) {
        try{

            String sql = "insert into users(username, password, first_name,last_name,email,age)" +
                    "values(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getUserName());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getFirstName());
            pstmt.setString(4,user.getLastName());
            pstmt.setString(5,user.getUserEmail());
            pstmt.setString(6, Integer.toString(user.getAge()));

            ResultSet rs = pstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
