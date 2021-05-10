package com.revature.fsmapp.daos;

import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.util.ConnectionFactory;
import com.revature.fsmapp.util.collection.ArrayList;
import com.revature.fsmapp.util.collection.List;

import java.sql.*;

public class AccountDAO {

    public AccountDAO() {

    }

    public List<Account> getAccountsByUserID(AppUser user) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            List<Account> accounts = new ArrayList<>();
            String sql = "";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getUserID());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                String password = user.getPassword();
                account.setAccountNumber(rs.getInt("accountid"),password);
                account.setBalance(rs.getDouble("balance"),password);
                //TODO implement logic to check account type, need to implement views in db to allow this functionality
                switch (rs.getString("account_type")){
                    case "Checking":
                        break;
                }
                accounts.add(account);
            }
            return accounts;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }

    public double getAccountBalance(Account account) {
        int accountNumber = account.getAccountNumber();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select accounts.balance from accounts where account.id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,Integer.toString(accountNumber));
            ResultSet rs = pstmt.executeQuery();
            return rs.getDouble("balance");

        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void openAccount(int userID, String accountName, double initialBalance) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            int newID = 0;
            String sql = "";
            PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"account_id"});

            stmt.setString(1, accountName);
            stmt.setDouble(2, initialBalance);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected != 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    newID = rs.getInt("account_id");
                }
            }

            linkAccount(userID, newID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void linkAccount(int userID, int accountID) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, accountID);
            stmt.setInt(2, userID);

            int result = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addToBalance(double amount, int accountID) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);

            int rowsUpdated = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deductFromBalance(double amount, int accountID) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);

            int rowsUpdated = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean accountExists(int accountID) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountID);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}




