package com.bank.account.manager.dao;

import com.bank.account.manager.model.User;
import com.bank.account.manager.validation.Connect;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


   /* public static void createUserTable(){


        String url = "jdbc:sqlite:C:\\Users\\LOREDANA\\Desktop\\Java curs\\Project db\\BankAccount.db";

        String sql = "CREATE TABLE IF NOT EXISTS USERS(\n"
                + " ID INTEGER PRIMARY KEY,\n"
                + " USERNAME TEXT NOT NULL,\n"
                + " USERNAME TEXT NOT NULL,\n"
                + " PASSWORD TEXT NOT NULL,\n"
                + ")";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\LOREDANA\\Desktop\\Java curs\\Project db\\BankAccount.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void insert(User user)throws SQLException {
        String sql = "INSERT INTO user(id,userName,name, password) VALUES(?,?,?,?)";
       // long id,String userName, String name,String password
        try{
            Connection conn = connect();//this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,user.getId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insertUser(List<User> users)throws SQLException{
        System.out.println("Inserting test users...");
        for (User value : users) {
            insert(value);
        }
    }*/
//Connect connect=me
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS USER("
            + " ID INTEGER PRIMARY KEY,"
            + " USER_NAME TEXT NOT NULL,"
            + " NAME TEXT NOT NULL,"
            + " PASSWORD TEXT NOT NULL"
            + ")";
    private static final String INSERT = "INSERT INTO USER(ID, USER_NAME, NAME, PASSWORD) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM USER ORDER BY NAME";
    private static final String UPDATE_PASSWORD = "UPDATE USER SET PASSWORD = ? WHERE ID = ?";
    private static final String DELETE_USER = "DELETE FROM USER WHERE ID = ?";

    public static void createUserTable() throws SQLException {
        try(Connection connection = Connect.connect();
            Statement statement = connection.createStatement()) {
            statement.execute( CREATE_TABLE );
        }
    }

    public void insertUser(List<User> users) throws SQLException {
        for( User user : users ) {
            insertUser(user);
        }
    }

    public void insertUser(User user) throws SQLException {
        try(Connection connection = Connect.connect();
            PreparedStatement ps = connection.prepareStatement(INSERT)){
            ps.setLong(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getName());
            ps.setString(4, user.getPassword());

            int rowsNo = ps.executeUpdate();

            System.out.println("Inserted " + rowsNo + " row(s)");
        }
    }
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try(Connection connection = Connect.connect();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()) {
                users.add(fromResultSet(rs));
            }
        }

        return users;
    }

    public void updateUserPassword(long userId, String newPassword) throws SQLException {
        try(Connection connection = Connect.connect();
            PreparedStatement ps = connection.prepareStatement(UPDATE_PASSWORD)){
            ps.setString(1, newPassword);
            ps.setLong(2, userId);

            int rowsNo = ps.executeUpdate();

            System.out.println( "Updated " + rowsNo + " row(s)");
        }
    }

    public void deleteUser( long Id ) throws SQLException {
        try(Connection connection = Connect.connect();
            PreparedStatement ps = connection.prepareStatement(DELETE_USER)){
            ps.setLong(1, Id);

            int rowsNo = ps.executeUpdate();

            System.out.println( "Deleted " + rowsNo + " row(s)");
        }
    }

    private User fromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        String userName = rs.getString("USER_NAME");
        String name = rs.getString("NAME");
        String password = rs.getString("PASSWORD");

        return new User(id, userName, name,password);
    }

}
