package com.bank.account.manager.menu;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.service.AccountService;
import com.bank.account.manager.service.Transaction;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        List<Account>accounts=new ArrayList<>();
        Account a1=new Account(1,"RO12345",5000, Currency.valueOf("RON"), Type.valueOf("SAVINGS"));
        int numAccounts = 0;
        int choice;
        Transaction transaction=new Transaction();
        AccountService accountService=new AccountService();

        do {
            choice = menu(keyboard);
            System.out.println();
            if (choice == 1) {
                accountService.addAccount(a1);
            } else if (choice == 2) {
                transaction.deposit(new Account(),500);
            } else if (choice == 3) {
                transaction.withdraw(new Account(),100);
            } else if (choice == 4) {
                transaction.printAccountInfo(1);
            } else {
                System.out.println("Thank you!");
            }
            System.out.println();


        } while (choice != 5);

    }
    public static int accountMenu(Scanner keyboard) {
        System.out.println("Select account type: ");
        System.out.println("1. Checking Account ");
        System.out.println("2. Savings Account");
        int choice;
        do {
            System.out.println("Enter choice: ");
            choice = keyboard.nextInt();
        } while (choice < 1 || choice > 2);
        return choice;
    }

    public static int menu(Scanner keyboard) {
        System.out.println("Bank account menu");
        System.out.println("1. Create a new account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Print Account info");
        System.out.println("5. Exit");
        int choice;
        do {
            System.out.println("enter choice: ");
            choice = keyboard.nextInt();
        } while (choice < 1 || choice > 5);
        return choice;
    }

}
