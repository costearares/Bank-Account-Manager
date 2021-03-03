package com.bank.account.manager.dao;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.User;
import com.bank.account.manager.service.AccountService;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.validation.Connect;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ACCOUNT("
            + " ID INTEGER PRIMARY KEY,"
            + " ACCOUNT_NUMBER TEXT NOT NULL,"
            + " BALANCE DOUBLE NOT NULL,"
            + " CURRENCY TEXT NOT NULL ,"
            + " TYPE TEXT"
            + ")";
    private static final String INSERT = "INSERT INTO ACCOUNT(ID, ACCOUNT_NUMBER, BALANCE, CURRENCY, TYPE) VALUES (?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ACCOUNT ORDER BY BALANCE";
    private static final String UPDATE_BALANCE = "UPDATE ACCOUNT SET BALANCE = ? WHERE ID = ?";
    private static final String DELETE_ACCOUNT = "DELETE FROM ACCOUNT WHERE ID = ?";

    public static void createAccountTable() throws SQLException {
        try(Connection connection = Connect.connect();
            Statement statement = connection.createStatement()) {
            statement.execute( CREATE_TABLE );
        }
    }
    public void insertAccount(List<Account> accounts) throws SQLException {
        for( Account account : accounts ) {
            insertAccount(account);
        }
    }

   public void insertAccount(Account account) throws SQLException {

        try(Connection connection = Connect.connect();

            PreparedStatement ps = connection.prepareStatement(INSERT)){
            ps.setLong(1, account.getId());
            ps.setString(2, account.getAccountNumber());
            ps.setDouble(3, account.getBalance());
            ps.setString(4, account.getCurrency().name());
            ps.setString(5, account.getType().name());
            int rowsNo = ps.executeUpdate();

            System.out.println("Inserted " + rowsNo + " row(s)");
        }
    }
   /* public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();

        try(Connection connection = Connect.connect();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()) {
                accounts.add(fromResultSet(rs));
            }
        }

        return accounts;
    }*/

    public void updateAccountBalance(long userId, Double newBalance) throws SQLException {
        try(Connection connection = Connect.connect();
            PreparedStatement ps = connection.prepareStatement(UPDATE_BALANCE)){
            ps.setDouble(1, newBalance);
            ps.setLong(2, userId);

            int rowsNo = ps.executeUpdate();

            System.out.println( "Updated " + rowsNo + " row(s)");
        }
    }

    public void deleteAccount( long Id ) throws SQLException {
        try(Connection connection = Connect.connect();
            PreparedStatement ps = connection.prepareStatement(DELETE_ACCOUNT)){
            ps.setLong(1, Id);

            int rowsNo = ps.executeUpdate();

            System.out.println( "Deleted " + rowsNo + " row(s)");
        }
    }

   /* private Account fromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        String accountNumber = rs.getString("ACCOUNT_NUMBER");
        double balance = rs.getDouble("BALANCE");
        String currency = rs.getString("CURRENCY");
        String type = rs.getString("TYPE");

        return new Account(id, accountNumber, balance,currency,type);
    }*/
}
