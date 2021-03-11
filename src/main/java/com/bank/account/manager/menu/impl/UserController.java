package com.bank.account.manager.menu.impl;

import com.bank.account.manager.menu.IMenu;
import com.bank.account.manager.model.User;
import com.bank.account.manager.service.UserService;

import java.util.Scanner;

public class UserController implements IMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IMenu menu = new Menu();
    private static final UserService userService = new UserService();

    public void chooseOption(User user) {
        try {
            int userChoice;

            do {
                System.out.println("----------------------------");
                System.out.println("1) Add User");
                System.out.println("2) Update User");
                System.out.println("3) Delete User");
                System.out.println("4) Go back");
                System.out.println("5) Exit");
                System.out.println("----------------------------");
                System.out.print("Enter choice [1-5]: ");

                userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1:
                        userService.addUser();
                        break;
                    case 2:
                        userService.updateUser(user);
                        break;
                    case 3:
                        userService.deleteUser(user);
                        break;
                    case 4:
                        menu.chooseOption(user);
                        break;
                    case 5:
                        System.exit(0);
                }
            }
            while (userChoice != '5');
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
