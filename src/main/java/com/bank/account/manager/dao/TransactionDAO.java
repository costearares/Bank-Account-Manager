package com.bank.account.manager.dao;

import com.bank.account.manager.model.Transaction;
import com.bank.account.manager.validation.Connect;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionDAO {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS TRANSACTIONS ("
            + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " ACCOUNT_NUMBER TEXT, "
            + " AMOUNT DOUBLE NOT NULL, "
            + " TYPE VARCHAR NOT NULL, "
            + " CREATED_DATE DATE NOT NULL "
            + ")";
    private static final String INSERT = "INSERT INTO TRANSACTIONS(ACCOUNT_NUMBER, AMOUNT, TYPE, CREATED_DATE) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM TRANSACTIONS ORDER BY ID";
    private static final String DELETE_TRANSACTIONS = "DELETE FROM TRANSACTIONS WHERE ID = ?";


    public static void createTransactionsTable() throws SQLException {
        try (Connection connection = Connect.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        }
    }

    public void insertTransaction(List<Transaction> transactions) throws SQLException {
        for (Transaction transaction : transactions) {
            insertTransaction(transaction);
        }
    }

    public int insertTransaction(Transaction transaction) throws SQLException {
        int rowsNo;
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, transaction.getAccountNumber());
            ps.setDouble(2, transaction.getAmount());
            ps.setString(3, transaction.getType().name());
            ps.setDate(4, new Date(System.currentTimeMillis()));

            rowsNo = ps.executeUpdate();
            System.out.println("Inserted " + rowsNo + " row(s)");
        }
        return rowsNo;
    }

    public List<Transaction> getTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                transactions.add(fromResultSet(rs));
            }
        }
        return transactions;
    }


    public void deleteTransaction(long Id) throws SQLException {
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(DELETE_TRANSACTIONS)) {
            ps.setLong(1, Id);

            int rowsNo = ps.executeUpdate();
            System.out.println("Deleted " + rowsNo + " row(s)");
        }
    }

    private Transaction fromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        String type = rs.getString("TYPE");
        LocalDate date = rs.getDate("DATE").toLocalDate();
        double value = rs.getDouble("VALUE");
        String accountNumber = rs.getString("ACCOUNT_NUMBER");
        //new Transaction(id, type, date, value, accountNumber)
        return new Transaction();
    }
}
