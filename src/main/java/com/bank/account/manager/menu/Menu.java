package com.bank.account.manager.menu;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.service.AccountService;
import com.bank.account.manager.service.Transaction;
import com.bank.account.manager.service.UserService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        Transaction transaction = new Transaction();
        AccountService accountService = new AccountService();
        UserService userService = new UserService();

        int user_choice = 2;

        do {
            System.out.println();
            System.out.println("1) Login");
            System.out.println("2) Open a new bank account");
            System.out.println("3) Deposit to a bank account");
            System.out.println("4) Withdraw to bank account");
            System.out.println("5) Get all accounts");
            System.out.println("6) Transfer");
            System.out.println("7) Add User");
            System.out.println("8) Exit");
            System.out.println();
            System.out.print("Enter choice [1-9]: ");
            user_choice = s.nextInt();
            switch (user_choice) {
                case 1:
                    userService.login();
                    break;
                case 2:
                    accountService.openNewAccount();
                    accountService.getAllAccounts();
                    break;
                case 3:
                    transaction.deposit();
                    break;
                case 4:
                    transaction.withdraw();
                    break;
                case 5:
                    printInfo(accountService.getAllAccounts());
                    break;
                case 6:
                    transaction.transferTo();
                    break;
                case 7:
                    userService.addUser();
                    userService.getAllUsers();
                    break;
                case 8:
                    System.out.println("Here are the balances for each account:");
                case 9:
                    System.exit(0);
            }
        }
        while (user_choice != '9');
    }

    public static void printInfo(List<Account> accounts) {
        accounts.forEach(System.out::println);
    }
}
