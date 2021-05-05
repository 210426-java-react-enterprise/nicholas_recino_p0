package com.revature.fsmapp.daos;

import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection conn;

    public UserDAO(Connection conn){
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
// TODO need to implement a selective update
    public void updateUser(AppUser user) {

    }
    public AppUser findUserByUsernameAndPassword(String username,String password){
        AppUser user = null;
        try{

            String sql = "SELECT * FROM users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                user = new AppUser();
                user.setUserID(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUserEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }
}
