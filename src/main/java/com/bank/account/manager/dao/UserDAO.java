package com.bank.account.manager.dao;

import com.bank.account.manager.model.User;
import com.bank.account.manager.validation.Connect;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS USER("
            + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " USERNAME TEXT NOT NULL,"
            + " NAME TEXT NOT NULL,"
            + " PASSWORD TEXT NOT NULL"
            + ")";

    private static final String INSERT = "INSERT INTO USER( USERNAME, NAME, PASSWORD) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM USER ORDER BY NAME";
    private static final String UPDATE_USER = "UPDATE USER SET USERNAME=?,NAME=?, PASSWORD = ? WHERE ID=?";
    private static final String DELETE_USER = "DELETE FROM USER WHERE USERNAME = ?";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM USER WHERE USERNAME=?";


    public static void createUserTable() throws SQLException {
        try (Connection connection = Connect.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        }
    }

    public static void insertUser(List<User> users) throws SQLException {
        for (User user : users) {
            insertUser(users);
        }
    }

    public int insertUser(User user) {
        int rowsNo;
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            rowsNo = ps.executeUpdate();
            System.out.println("Inserted " + rowsNo + " row(s)");
        } catch (SQLException ex) {
            throw new RuntimeException("Error while inserting new user: " + ex.getMessage());
        }
        return rowsNo;
    }

    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(fromResultSet(rs));
            }
        }
        return users;
    }

    public User getUserByUserName(User imputUser) throws SQLException {
        User user1 = null;
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            ps.setString(1, imputUser.getUsername());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user1 = fromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while getting user: " + ex.getMessage());
        }
        return user1;
    }

    public User updateUser(User user) throws SQLException {
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setLong(4, user.getId());

            int rowsNo = ps.executeUpdate();

            System.out.println("Updated " + rowsNo + " row(s)");
        }
        return user;
    }

    public void deleteUser(User user) throws SQLException {
        try (Connection connection = Connect.connect();
             PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setString(1, user.getUsername());

            int rowsNo = ps.executeUpdate();

            System.out.println("Deleted " + rowsNo + " row(s)");
        }
    }

    private User fromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        String userName = rs.getString("USERNAME");
        String name = rs.getString("NAME");
        String password = rs.getString("PASSWORD");

        return new User(id, userName, name, password);
    }

}
