package com.bank.account.manager.service;

import com.bank.account.manager.dao.AccountDAO;
import com.bank.account.manager.dao.UserDAO;
import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private static final Scanner keyboard = new Scanner(System.in);
    private UserDAO userDAO = new UserDAO();
    private AccountDAO accountDAO = new AccountDAO();

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
            throw new RuntimeException("User already exists!");
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

    public void deleteUser(User user) throws SQLException {
        System.out.println("Enter a username: ");
        String userName = keyboard.next();
        user.setUsername(userName);

        Account account=new Account();
        account.setUserId(user.getId());
        accountDAO.deleteAccount(account);
        userDAO.deleteUser(user);
    }

    public User updateUserService(User user) throws SQLException {
        User dbUser = new User();
        if (user.equals(userDAO.getUserByUserName(user))) {
            System.out.println("Enter new username: ");
            String userName = keyboard.next();
            System.out.println("Enter a new name: ");
            String name = keyboard.next();
            System.out.println("Enter a new password: ");
            String password = keyboard.next();

            user.setUsername(userName);
            user.setName(name);
            user.setPassword(password);

            dbUser = userDAO.getUserByUserName(user);
            userDAO.updateUser(user);
        }
        return dbUser;
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
