package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.User;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private static final Scanner keyboard = new Scanner(System.in);
    private static List<User>users;

    static {
        users=new ArrayList<>();
        User user1=new User(1, "Ion", "Ion Ionescu", "ionel", 5000) ;
        User user2= new User(2, "Ana", "Ana Barbu", "parola", 2000);
    }


    public void login() {
        System.out.println("Login: ");
        System.out.println("Enter a username: ");
        String userName = keyboard.next();
        System.out.println("Enter a password: ");
        String password = keyboard.next();
        for (User user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                System.out.println("Login successfully!");
                break;
            } else {
                System.out.println("Wrong name or password");
                break;
            }
        }
    }

    public void addUser() {

        System.out.println("Enter a account id: ");
        long id = keyboard.nextLong();
        System.out.println("Enter a user name: ");
        String userName = keyboard.next();
        System.out.println("Enter a name: ");
        String name = keyboard.next();
        System.out.println("Enter a password: ");
        String password = keyboard.next();
        System.out.println("Enter a opening balance: ");
        double balance = keyboard.nextDouble();
        for (User value : users) {
            if (value.getId() == id) {
                System.out.println("This user already exists!");
                break;
            } else {
                User user1 = new User(id, userName, name, password, balance);
                System.out.println("The new user is: " + user1.toString());
                users.add(user1);
                break;

            }
        }
    }

    public void getAllUsers() {
        System.out.println("All users: " + users.toString());
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public void updateUser() {

    }


    public void getUserById() {

    }

}
