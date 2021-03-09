package com.bank.account.manager.menu;

import com.bank.account.manager.model.User;
import com.bank.account.manager.service.TransactionService;
import com.bank.account.manager.util.TransactionType;

import java.sql.SQLException;
import java.util.Scanner;

public class TransactionController implements IMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TransactionService transactionService = new TransactionService();
    private static final Menu menu = new Menu();

    public void chooseOption(User user) throws SQLException {
        int userChoice;

        do {
            System.out.println("----------------------------");
            System.out.println("1) Deposit to a bank account");
            System.out.println("2) Withdraw to bank account");
            System.out.println("3) Transfer");
            System.out.println("4) Transfers history");
            System.out.println("5) Go back");
            System.out.println("6) Exit");
            System.out.println("----------------------------");
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
                    menu.chooseOption(user);
                    break;
                case 6:
                    System.exit(0);
            }
        }
        while (userChoice != '6');
    }
}
