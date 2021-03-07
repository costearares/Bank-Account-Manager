package com.bank.account.manager.menu;

import com.bank.account.manager.dao.AccountDAO;
import com.bank.account.manager.dao.TransactionDAO;
import com.bank.account.manager.dao.UserDAO;
import com.bank.account.manager.model.Account;
import com.bank.account.manager.service.AccountService;
import com.bank.account.manager.service.TransactionService;
import com.bank.account.manager.service.UserService;
import com.bank.account.manager.util.TransactionType;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    private static final TransactionService transactionService = new TransactionService();
    private static final AccountService accountService = new AccountService();
    private static final UserService userService = new UserService();
    private static final FinancialController financialController = new FinancialController();
    private static final UserController userController = new UserController();

    public static void main(String[] args) throws SQLException {

        UserDAO.createUserTable();
        AccountDAO.createAccountTable();
        TransactionDAO.createTransactionsTable();

        int userChoice = 2;

        do {
            System.out.println();
            System.out.println("1) New User");
            System.out.println("2) Login");
            System.out.println("3) Exit");
            System.out.println();
            System.out.print("Enter choice [1-3]: ");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    userService.addUser();
                    break;
                case 2:
                    userService.login();
                    userController.chooseOption();
                    break;
                case 3:
                    System.out.println("Thank you!");
                case 4:
                    System.exit(0);
            }
        }
        while (userChoice != '4');
    }

    public static void printInfo(List<Account> accounts) {
        accounts.forEach(System.out::println);
    }
}
