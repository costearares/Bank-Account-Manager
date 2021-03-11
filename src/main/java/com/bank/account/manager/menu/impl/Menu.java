package com.bank.account.manager.menu.impl;

import com.bank.account.manager.menu.IMenu;
import com.bank.account.manager.model.User;

import java.util.Scanner;

public class Menu implements IMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IMenu transactionController = new TransactionController();
    private static final IMenu userController = new UserController();
    private static final IMenu accountController = new AccountController();

    public void chooseOption(User user) {
        int userChoice;

        do {
            System.out.println("----------------------------");
            System.out.println("1) Update Profile");
            System.out.println("2) Account Management");
            System.out.println("3) Financial services");
            System.out.println("4) Exit");
            System.out.println("----------------------------");
            System.out.print("Enter choice [1-3]: ");

            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    userController.chooseOption(user);
                    break;
                case 2:
                    accountController.chooseOption(user);
                    break;
                case 3:
                    transactionController.chooseOption(user);
                    break;
                case 4:
                    System.exit(0);
            }
        }
        while (userChoice != '4');
    }

}
