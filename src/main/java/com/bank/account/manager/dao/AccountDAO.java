package com.bank.account.manager.dao;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.util.AccountType;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.validation.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ACCOUNT("
            + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " ACCOUNT_NUMBER TEXT NOT NULL,"
            + " BALANCE DOUBLE NOT NULL,"
            + " CURRENCY TEXT NOT NULL ,"
            + " TYPE TEXT,"
            + " USER_ID NUMERIC REFERENCES  USER(ID)"
            + ")";
    private static final String INSERT = "INSERT INTO ACCOUNT ( ACCOUNT_NUMBER, BALANCE, CURRENCY, TYPE, USER_ID) VALUES (?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ACCOUNT ORDER BY BALANCE";
    private static final String UPDATE_BALANCE = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_NUMBER = ?";
    private static final String DELETE_ACCOUNT = "DELETE FROM ACCOUNT WHERE ACCOUNT_NUMBER = ?";
    private static final String DELETE_ACCOUNT_BY_USER_ID = "DELETE FROM ACCOUNT WHERE USER_ID = ?";
    private static final String SELECT_ACCOUNT_BY_NUMBER = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER = ?";
    private static final String SELECT_ACCOUNTS_BY_USER = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";


    public static void createAccountTable() throws SQLException {
        try (Connection connection = Connect.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        }
    }

    public int insertAccount(Account account) throws SQLException {
        int rowNo;
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {

            ps.setString(1, account.getAccountNumber());
            ps.setDouble(2, account.getBalance());
            ps.setString(3, account.getCurrency().name());
            ps.setString(4, account.getType().name());
            ps.setLong(5, account.getUserId());
            rowNo = ps.executeUpdate();
        }
        return rowNo;
    }

    public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                accounts.add(fromResultSet(rs));
            }
        }
        return accounts;
    }

    public List<Account> getAccountsByUserID(Account account) throws SQLException {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(SELECT_ACCOUNTS_BY_USER)) {
            ps.setLong(1, account.getUserId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(fromResultSet(rs));
            }
        }
        return accounts;
    }

    public void updateBalance(Account account) throws SQLException {
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(UPDATE_BALANCE)) {
            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getAccountNumber());

            int rowsNo = ps.executeUpdate();

            System.out.println("Updated " + rowsNo + " row(s)");
        }
    }

    public void deleteAccount(Account account) throws SQLException {
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(DELETE_ACCOUNT)) {
            ps.setString(1, account.getAccountNumber());

            int rowsNo = ps.executeUpdate();

            System.out.println("Deleted " + rowsNo + " row(s)");
        }
    }

    public void deleteAccountByUserId(Account account) throws SQLException {
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(DELETE_ACCOUNT_BY_USER_ID)) {
            ps.setLong(1, account.getUserId());

            int rowsNo = ps.executeUpdate();

            System.out.println("Deleted " + rowsNo + " row(s)");
        }
    }

    public Account getAccountByAccountNumber(Account account) {
        Account dbAccount = null;
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(SELECT_ACCOUNT_BY_NUMBER)) {
            ps.setString(1, account.getAccountNumber());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                dbAccount = fromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while getting account: " + ex.getMessage());
        }

        return dbAccount;
    }

    private Account fromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        String accountNumber = rs.getString("ACCOUNT_NUMBER");
        double balance = rs.getDouble("BALANCE");
        Currency currency = Currency.valueOf(rs.getString("CURRENCY"));
        AccountType accountType = AccountType.valueOf(rs.getString("TYPE"));
        long user_id = rs.getLong("USER_ID");

        return new Account(id, accountNumber, balance, currency, accountType, user_id);
    }

    public void insertAccount(List<Account> accounts) throws SQLException {
        for (Account account : accounts) {
            insertAccount(account);
        }
    }
}
