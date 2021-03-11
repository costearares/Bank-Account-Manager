package com.bank.account.manager.menu.impl;

import com.bank.account.manager.menu.IMenu;
import com.bank.account.manager.model.User;
import com.bank.account.manager.service.AccountService;

import java.util.Scanner;

public class AccountController implements IMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AccountService accountService = new AccountService();
    private static final IMenu menu = new Menu();

    public void chooseOption(User user) {
        try {
            int userChoice;
            do {
                System.out.println("----------------------------");
                System.out.println("1) Available accounts");
                System.out.println("2) Add Account");
                System.out.println("3) Delete Account");
                System.out.println("4) Go back");
                System.out.println("5) Exit");
                System.out.println("----------------------------");
                System.out.print("Enter choice [1-5]: ");

                userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1:
                        System.out.println(accountService.getAllAccounts());
                    case 2:
                        accountService.openNewAccount(user);
                        break;
                    case 3:
                        accountService.deleteAccount();
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
