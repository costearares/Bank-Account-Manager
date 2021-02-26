package com.bank.account.manager.menu;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.User;
import com.bank.account.manager.service.AccountService;
import com.bank.account.manager.service.Transaction;
import com.bank.account.manager.service.UserService;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        List<User> users = new ArrayList<User>();
        User u1 = new User(1, "Ion", "Ion Ionescu", "ionel", 5000);
        User u2 = new User(2, "Ana", "Ana Barbu", "parola", 2000);
        users.add(u1);
        users.add(u2);
        List<Account> accounts = new ArrayList<>();
        Account a1 = new Account(1, "1234", 5000, Currency.valueOf("RON"), Type.valueOf("SAVINGS"));
        Account a2 = new Account(2, "1784", 2000, Currency.valueOf("RON"), Type.valueOf("SAVINGS"));
        accounts.add(a1);
        accounts.add(a2);
        int numAccounts = 2;
        int choice;
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
                    //transaction.deposit();
                    System.out.println("Enter a account id");
                    long idd = s.nextLong();
                    System.out.println("Enter a deposit amount");
                    double da = s.nextDouble();
                    for (Account a : accounts) {
                        if (a.getId() == idd) {
                            a.deposiT(da);
                            System.out.println("New balance: " + a.getBalance());
                        }
                    }

                    break;
                case 4:
                   // transaction.withdraw();
                    System.out.println("Enter a account number");
                    long idw = s.nextLong();
                    System.out.println("Enter a withdraw amount");
                    double wa = s.nextDouble();
                    for (Account a : accounts) {
                        if (a.getId() == idw) {
                            double balance = a.getBalance() - wa;
                            System.out.println("New balance: " + balance);
                        }
                    }
                    break;
                case 5:
                   //accountService.getAllAccounts();
                    System.out.println("All accounts: "+ accounts.toString());
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
}
