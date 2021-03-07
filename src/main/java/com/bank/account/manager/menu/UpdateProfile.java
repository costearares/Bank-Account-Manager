package com.bank.account.manager.menu;

import com.bank.account.manager.service.AccountService;
import com.bank.account.manager.service.UserService;
import com.bank.account.manager.util.TransactionType;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateProfile {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AccountService accountService = new AccountService();
    private static final UserService userService = new UserService();
    private static final UserController userController = new UserController();

    public void profile() throws SQLException {
        int userChoice = 2;

        do {
            System.out.println("1) Add User");
            System.out.println("2) Delete User");
            System.out.println("3) Add Account");
            System.out.println("4) Go back");
            System.out.println("5) Exit");
            System.out.println();
            System.out.print("Enter choice [1-5]: ");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    userService.addUser();
                    break;
                case 2:
                    userService.deleteUser("Ana");
                    break;
                case 3:
                    accountService.openNewAccount();
                    break;
                case 4:
                    userController.chooseOption();
                    break;
                case 5:
                    System.exit(0);
            }
        }
        while (userChoice != '5');
    }
}
