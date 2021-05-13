package com.revature.fsmapp.daos;

import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.models.Transaction;
import com.revature.fsmapp.util.ConnectionFactory;
import com.revature.fsmapp.util.collection.ArrayList;
import com.revature.fsmapp.util.collection.List;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class AccountDAO {

    private Connection conn;

    public AccountDAO(Connection conn){
        this.conn = conn;
    }

    public List<Account> getAccountsByUserID(AppUser user) {
        try{
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

    public boolean accountExists(int accountID) {

        try {
            String query = "select * from accounts where account_id = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
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

    public double getAccountBalance(Account account) {
        int accountNumber = account.getAccountNumber();
        try{
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

    public int openAccount(int userID, String pin, double initialBalance) {
        int newID = -1;
        try  {
            String sql = "insert into accounts(pin,balance,account_open) values(?,?,true)";
            PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"account_id"});
            stmt.setString(1, pin);
            stmt.setDouble(2, initialBalance);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    newID = rs.getInt("account_id");
                    linkAccount(userID, newID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newID;
    }

    public boolean linkAccount(int userID, int accountID) {
        if(accountID == -1)
            return false;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into account_access_permissions(user_id,account_id) values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userID);
            stmt.setInt(2, accountID);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setBalance(double amount, int accountID) {
        try {
            String sql = "update accounts set balance = ? where account_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean accountOpen(int accountID) {
        try{
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

    public void addBalance(double amount, int accountID) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "update accounts set balance = balance + ? where account_id = ?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);

            int rowsUpdated = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void subtractBalance(double amount, int accountID) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "update accounts set balance = balance - ? where account_id = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);

            int rowsUpdated = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Transaction> saveTransaction(String sender, int senderID,int recipientID,double amount) {

        Optional<Transaction> _transaction = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "";

            PreparedStatement stmt = conn.prepareStatement(sql);


            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated != 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    Transaction transaction = transactionProcessing(rs);
                    transaction.setTransactionDate(rs.getTimestamp("transaction_Date").toLocalDateTime());
                    _transaction = Optional.of(transaction);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return _transaction;
    }

    private Transaction transactionProcessing(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(rs.getInt("transactionid"));
        transaction.setSender(rs.getString("sender_name"));
        transaction.setSenderAccount(rs.getInt("sender_account"));
        transaction.setRecipient(rs.getString("recipient_name"));
        transaction.setRecipientAccount(rs.getInt("recipient_account"));
        transaction.setAmount(rs.getDouble("amount"));
        return transaction;
    }

}




