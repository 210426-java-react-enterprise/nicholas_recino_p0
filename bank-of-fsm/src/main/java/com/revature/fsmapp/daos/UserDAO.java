package com.revature.fsmapp.daos;

import com.revature.fsmapp.models.AppUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection conn;

    public UserDAO(Connection conn){
        this.conn = conn;
    }

    public boolean saveUser(AppUser user) {
        try{
            String sql = "insert into users(username, password) " +
                    "values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql,new String[]{"user_id"});
            pstmt.setString(1,user.getUserName());
            pstmt.setString(2,user.getPassword());
            int rowsInserted = pstmt.executeUpdate();
            if(rowsInserted!=0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    user.setUserID(rs.getInt("user_id"));
                }
            }
            String sql2 = "insert into personal_information(user_id,first_name,last_name,age,email) " +
                    "values(?,?,?,?,?)";
            PreparedStatement pstmt1 = conn.prepareStatement(sql2);
            pstmt1.setInt(1,user.getUserID());
            pstmt1.setString(2,user.getFirstName());
            pstmt1.setString(3,user.getLastName());
            pstmt1.setInt(4,user.getAge());
            pstmt1.setString(5,user.getUserEmail());
            pstmt1.executeUpdate();



        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        System.out.println("--------------------");
        return true;
    }
// TODO need to implement a selective update
    public void updateUser(AppUser user) {

    }
    public AppUser findUserByUsernameAndPassword(String username,String password){
        AppUser user = null;
        try{

            String sql = "SELECT * FROM users_view where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                user = generateAppUserByResultSet(rs);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public AppUser findUserByEmail(String email) {
        AppUser user = null;
        try {
            String sql = "SELECT * FROM users_view where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,email);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                user = generateAppUserByResultSet(rs);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }
    private AppUser generateAppUserByResultSet(ResultSet rs) throws SQLException{
        AppUser user = new AppUser();
        user.setUserID(rs.getInt("user_id"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUserEmail(rs.getString("email"));
        user.setAge(rs.getInt("age"));
        return user;
    }
}
