package com.bank.account.manager.service;

import com.bank.account.manager.dao.UserDAO;
import com.bank.account.manager.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private static final Scanner keyboard = new Scanner(System.in);
    private UserDAO userDAO = new UserDAO();

    public User login() throws SQLException {
        System.out.println("Login: ");
        System.out.println("Enter a username: ");
        String userName = keyboard.next();
        System.out.println("Enter a password: ");
        String password = keyboard.next();

        User user = new User();
        user.setUsername(userName);
        user.setName(userName);
        user.setPassword(password);

        return userDAO.getUserByUserName(user);
    }

    public int addUser() throws SQLException {
        System.out.println("Enter a username: ");
        String userName = keyboard.next();
        System.out.println("Enter a name: ");
        String name = keyboard.next();
        System.out.println("Enter a password: ");
        String password = keyboard.next();

        User user = new User();
        user.setUsername(userName);
        user.setName(name);
        user.setPassword(password);
        User dbUser = userDAO.getUserByUserName(user);

        if (dbUser != null) {
            //throw ex
        }
        int rowsNo = userDAO.insertUser(user);
        System.out.println("The new User is: " + user);
        if (rowsNo == 0) {
            //throw e
        }
        System.out.println(rowsNo);
        return rowsNo;
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getUsers();
    }

    public void deleteUser() throws SQLException {
        System.out.println("Enter a username: ");
        String userName = keyboard.next();
        userDAO.deleteUser(userName);
    }

    public void updateUserPassword() throws SQLException {
        System.out.println("Enter a username: ");
        String userName = keyboard.next();
        System.out.println("Enter a new password: ");
        String password = keyboard.next();
        userDAO.updateUserPassword(userName, password);

    }

    public User getByUserName(User user) throws SQLException {
        for (User value : userDAO.getUsers()) {
            if (value.getUsername().equals(user.getUsername())) {
                return value;
            }
        }
        return null;
    }

}
