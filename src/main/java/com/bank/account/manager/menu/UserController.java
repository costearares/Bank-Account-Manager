package com.bank.account.manager.menu;

import com.bank.account.manager.model.User;
import com.bank.account.manager.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController implements IMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final Menu menu = new Menu();

    public void chooseOption(User user) throws SQLException {
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
                    try {
                        userService.addUser();
                    } catch (RuntimeException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    userService.updateUserService(user);
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
    }
}
