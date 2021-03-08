package com.bank.account.manager.validation;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {

    public static Connection connect() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        return DriverManager.getConnection("jdbc:sqlite:C:\\\\Users\\\\LOREDANA\\\\Desktop\\\\Java curs\\\\Project db\\\\BankAccount.db", config.toProperties());
    }

}
