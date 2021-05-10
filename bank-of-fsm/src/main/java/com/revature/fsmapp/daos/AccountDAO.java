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

            String sql = "select * from accounts_access_permissions where user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getUserID());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Account account = new Account(user.getPassword());
                String password = user.getPassword();
                account.setAccountNumber(rs.getInt("account_id"),password);
                account.setBalance(getAccountBalance(account),password);
                account.setPin(password,rs.getString("pin"));
                if(rs.getBoolean("account_open"))
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
            String sql = "select balance from accounts where account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,accountNumber);
            ResultSet rs = pstmt.executeQuery();
            return rs.getDouble("balance");

        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void openAccount(int userID, String pin, double initialBalance) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            int newID = 0;
            String sql = "insert into accounts(pin,balance,account_open) values(?,?,true)";
            PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"account_id"});
            stmt.setString(1, pin);
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
        if(accountID == 0)
            return;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into account_access_permissions(user_id,account_id) values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userID);
            stmt.setInt(2, accountID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBalance(double amount, int accountID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "update accounts set balance = ? where account_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean accountOpen(int accountID) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select account_open from accounts where account_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("account_open");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}




