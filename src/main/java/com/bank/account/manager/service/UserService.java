package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.User;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    Scanner keyboard = new Scanner(System.in);
    List<User> users = new ArrayList<>(List.of(new User(1, "Ion", "Ion Ionescu", "ionel", 5000),
            new User(2, "Ana", "Ana Barbu", "parola", 2000)));

    public void login() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Login: ");
        System.out.println("Enter a username: ");
        String n = keyboard.next();
        System.out.println("Enter a password: ");
        String p = keyboard.next();
        for (User user : users) {
            if (user.getUsername().equals(n) && user.getPassword().equals(p)) {
                System.out.println("Login successfully!");
                break;
            } else {
                System.out.println("Wrong name or password");
                break;
            }
        }
    }

    public void addUser() {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a account id: ");
        long nid = keyboard.nextLong();
        System.out.println("Enter a user name: ");
        String nun = keyboard.next();
        System.out.println("Enter a name: ");
        String nn = keyboard.next();
        System.out.println("Enter a password: ");
        String np = keyboard.next();
        System.out.println("Enter a opening balance: ");
        double nb = keyboard.nextDouble();


        for (User value : users) {
            if (value.getId() == nid) {
                System.out.println("This user already exists!");
                break;
            } else {
                User user1 = new User(nid, nun, nn, np, nb);
                System.out.println("The new account is: " + user1.toString());
                users.add(user1);
                break;

            }
        }
    }

    public void getAllUsers() {
        System.out.println("All users: "+ users.toString());
    }
    public void deleteUser(User user) {
        users.remove(user);
    }

    public void updateUser() {

    }


    public void getUserById() {

    }

}
