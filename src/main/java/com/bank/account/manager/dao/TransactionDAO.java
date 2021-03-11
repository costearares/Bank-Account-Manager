package com.bank.account.manager.dao;

import com.bank.account.manager.model.Transaction;
import com.bank.account.manager.util.TransactionType;
import com.bank.account.manager.validation.Connect;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TransactionDAO {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS TRANSACTIONS ("
            + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " ACCOUNT_NUMBER TEXT, "
            + " AMOUNT DOUBLE NOT NULL, "
            + " TYPE VARCHAR NOT NULL, "
            + " CREATED_DATE VARCHAR NOT NULL "
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

    public int insertTransaction(Transaction transaction) throws SQLException {
        int rowsNo;
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, transaction.getAccountNumber());
            ps.setDouble(2, transaction.getAmount());
            ps.setString(3, transaction.getType().name());
            ps.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

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
        String accountNumber = rs.getString("ACCOUNT_NUMBER");
        TransactionType type = TransactionType.valueOf(rs.getString("TYPE"));
        double value = rs.getDouble("AMOUNT");

        String createdDate = rs.getString("CREATED_DATE");
        LocalDate date = LocalDate.parse(createdDate);

        return new Transaction(id, accountNumber, type, value, date);
    }
}
