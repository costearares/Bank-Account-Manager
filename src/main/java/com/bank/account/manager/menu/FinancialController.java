package com.bank.account.manager.menu;

import com.bank.account.manager.service.TransactionService;
import com.bank.account.manager.util.TransactionType;

import java.sql.SQLException;
import java.util.Scanner;

public class FinancialController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TransactionService transactionService = new TransactionService();
    private static final UserController userController = new UserController();

    public void financial() throws SQLException {
        int userChoice = 2;

        do {

            System.out.println("1) Deposit to a bank account");
            System.out.println("2) Withdraw to bank account");
            System.out.println("3) Transfer");
            System.out.println("4) Transfers history");
            System.out.println("5) Go back");
            System.out.println("6) Exit");
            System.out.println();
            System.out.print("Enter choice [1-6]: ");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    transactionService.moveMoney(TransactionType.DEPOSIT);
                    break;
                case 2:
                    transactionService.moveMoney(TransactionType.WITHDRAW);
                    break;
                case 3:
                    transactionService.transferTo();
                    break;
                case 4:
                    transactionService.getAllTransactions();
                    break;
                case 5:
                    userController.chooseOption();
                    break;
                case 6:
                    System.exit(0);
            }
        }
        while (userChoice != '6');
    }
}
