package com.bank.account.manager.dao;

import com.bank.account.manager.model.Transaction;
import com.bank.account.manager.validation.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDAO {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS TRANSACTIONS ("
            + " ID INTEGER PRIMARY KEY,"
            + " TYPE TEXT NOT NULL,"
            + " DATE TEXT NOT NULL,"
            + " VALUE DOUBLE NOT NULL,"
            + " ACCOUNT_NUMBER TEXT "
            + ")";


    public static void createTransactionsTable() throws SQLException {
        try (Connection connection = Connect.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        }
    }
}
