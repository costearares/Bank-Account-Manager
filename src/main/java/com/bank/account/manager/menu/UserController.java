package com.bank.account.manager.menu;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final FinancialController financialController = new FinancialController();
    private static final UpdateProfile updateProfile = new UpdateProfile();

    public void chooseOption() throws SQLException {
        int userChoice = 2;

        do {
            System.out.println("1) Update Profile");
            System.out.println("2) Financial services");
            System.out.println("3) Exit");
            System.out.println();
            System.out.print("Enter choice [1-3]: ");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    updateProfile.profile();
                    break;
                case 2:
                    financialController.financial();
                    break;
                case 3:
                    System.exit(0);

            }
        }
        while (userChoice != '3');
    }

}
