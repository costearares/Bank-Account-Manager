package com.bank.account.manager.menu;

import com.bank.account.manager.dao.AccountDAO;
import com.bank.account.manager.dao.TransactionDAO;
import com.bank.account.manager.dao.UserDAO;
import com.bank.account.manager.menu.impl.Menu;
import com.bank.account.manager.model.User;
import com.bank.account.manager.service.UserService;

import java.util.Scanner;

public class Login {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final IMenu menu = new Menu();
    private static final Login login = new Login();

    public void initialize() {
        String message = "";

        try {
            UserDAO.createUserTable();
            AccountDAO.createAccountTable();
            TransactionDAO.createTransactionsTable();

            int userChoice;

            do {
                System.out.println("---------------");
                System.out.println("1) New User");
                System.out.println("2) Login");
                System.out.println("3) Exit");
                System.out.println("---------------");
                System.out.print("Enter choice [1-3]: ");

                userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1:
                        userService.addUser();
                        break;
                    case 2:
                        User loggedUser = userService.login();
                        if (loggedUser != null) {
                            message = "Login successfully!";
                        } else {
                            System.out.println("Wrong Username or Password.");
                            login.initialize();
                        }
                        menu.chooseOption(loggedUser);
                        break;
                    case 3:
                        System.out.println("Thank you!");
                    case 4:
                        System.exit(0);
                }
            }
            while (userChoice != '4');

        } catch (Exception ex) {
            System.out.println(message + " " + ex.getMessage());
        }
    }
}
