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

/**
 * The type Account dao.
 */
public class AccountDAO {

    private Connection conn;

    /**
     * Instantiates a new Account dao.
     *
     * @param conn the conn
     */
    public AccountDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Gets accounts by user id.
     *
     * @param user the user
     * @return the accounts by user id
     */
    public List<Account> getAccountsByUserID(AppUser user) {
        try{
            List<Account> accounts = new ArrayList<>();

            String sql = "select * from accounts where user_id = ?";
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

    /**
     * Account exists boolean.
     *
     * @param accountID the account id
     * @return the boolean
     */
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

    /**
     * Gets account balance.
     *
     * @param account the account
     * @return the account balance
     */
    public double getAccountBalance(Account account) {
        int accountNumber = account.getAccountNumber();
        try{
            String sql = "select balance from accounts where account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,accountNumber);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getDouble("balance");

        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Open account int.
     *
     * @param userID         the user id
     * @param pin            the pin
     * @param initialBalance the initial balance
     * @return the Account ID
     */
    public int openAccount(int userID, String pin, double initialBalance) {
        int newID = -1;
        try  {
            String sql = "insert into accounts(pin,balance,account_open,user_id) values(?,?,true,?)";
            PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"account_id"});
            stmt.setString(1, pin);
            stmt.setDouble(2, initialBalance);
            stmt.setInt(3,userID);
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

    /**
     * Link account boolean.
     *
     * @param userID    the user id
     * @param accountID the account id
     * @return Whether Account is linked validly or not
     */
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

    /**
     * Sets balance.
     *
     * @param amount    the amount
     * @param accountID the account id
     */
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

    /**
     * Returns true when account is still open.
     *
     * @param accountID the account id
     * @return the boolean
     */
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

    /**
     * Add balance to Account specified by accountID.
     *
     * @param amount    the amount
     * @param accountID the account id
     */
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

    /**
     * Subtract balance from Account specified by accountID.
     *
     * @param amount    the amount
     * @param accountID the account id
     */
    public void subtractBalance(double amount, int accountID,Account account) {
        double balance = account.getBalance()-amount;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String query = "update accounts set balance = ? where account_id = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, balance);
            stmt.setInt(2, accountID);

            int rowsUpdated = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save transaction on the database.
     *
     * @param sender      the sender
     * @param senderID    the sender id
     * @param recipientID the recipient id
     * @param amount      the amount
     * @return Transaction object to store in an array list to keep a record of.
     */
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
        transaction.setTransactionID(rs.getInt("transaction_id"));
        transaction.setSender(rs.getString("sender_name"));
        transaction.setSenderAccount(rs.getInt("sender_account"));
        transaction.setRecipient(rs.getString("recipient_name"));
        transaction.setRecipientAccount(rs.getInt("recipient_account"));
        transaction.setAmount(rs.getDouble("amount"));
        return transaction;
    }

}




