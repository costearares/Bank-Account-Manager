package com.bank.account.manager.validation;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
    /*public static void connect() {
        Connection conn = null;
        try {

            String url = "jdbc:sqlite:C:\\Users\\LOREDANA\\Desktop\\Java curs\\Project db\\BankAccount.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }*/
    public static Connection connect() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true); //enable FK support (disabled by default)
        return DriverManager.getConnection("jdbc:sqlite:C:\\\\Users\\\\LOREDANA\\\\Desktop\\\\Java curs\\\\Project db\\\\BankAccount.db", config.toProperties());
    }

}
