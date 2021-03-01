package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountService {
    private static final Scanner keyboard = new Scanner(System.in);
    private static List<Account> accounts;

    static {
        accounts = new ArrayList<>();
        Account a1 = new Account(1, "1234", 5000, Currency.valueOf("RON"), Type.valueOf("SAVINGS"));
        Account a2 = new Account(2, "1784", 2000, Currency.valueOf("RON"), Type.valueOf("SAVINGS"));
        accounts.add(a1);
        accounts.add(a2);
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public String selectAccountCurrency() {
        System.out.println("Choice currency: ");
        System.out.println("1: RON");
        System.out.println("2: EUR");
        String choice = keyboard.next();
        String currency = null;
        if (choice.equals("1")) {
            currency = "RON";
        } else if (choice.equals("2")) {
            currency = "EUR";
        } else {
            System.out.println("Enter again!");
        }
        return currency;
    }

    public String selectAccountType() {
        System.out.println("Choice account type: ");
        System.out.println("1: CURRENT");
        System.out.println("2: SAVINGS");
        String typeChoice = keyboard.next();
        String type = null;
        if (typeChoice.equals("1")) {
            type = "CURRENT";
        } else if (typeChoice.equals("2")) {
            type = "SAVINGS";
        } else {
            System.out.println("Enter again!");
        }
        return type;
    }

    public void openNewAccount() {
        System.out.println("Enter an account id: ");
        long id = keyboard.nextLong();
        System.out.println("Enter an account number: ");
        String accountNumber = keyboard.next();
        System.out.println("Enter a opening balance: ");
        double balance = keyboard.nextDouble();
        Account account = new Account(id, accountNumber, balance, Currency.valueOf(selectAccountCurrency()), Type.valueOf(selectAccountType()));
        for (Account value : accounts) {
            if (!value.getAccountNumber().equals(account.getAccountNumber())) {
                System.out.println("The new account is: " + account.toString());
                accounts.add(account);
                break;
            } else {
                System.out.println("This account already exists!");
            }
        }
    }

    public Account getAccountByID(long id) {
        Account account = new Account();
        for (Account value : accounts) {
            if (value.getId() == id) {
                account = value;
            }
        }
        return account;
    }

    public Account getAccountByAccNumber(Account inputAccount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(inputAccount.getAccountNumber())) {
                return account;
            }
        }
        return null;
    }

    public void printBalance() {
        System.out.println("Enter an account id: ");
        long id = keyboard.nextLong();
        for (Account value : accounts) {
            if (value.getId() == id) {
                System.out.println("Balance is: " + value.getBalance());
                break;
            } else {
                System.out.println("Account not found ");
            }
        }
    }


    public void removeAccount(Account account) {
        accounts.remove(account);

    }

    public void updateDepositBalance(Account account) {
        for (Account accountFromList : accounts) {
            if (accountFromList.getAccountNumber().equals(account.getAccountNumber())) {
                double oldBalance = accountFromList.getBalance();
                accountFromList.setBalance(oldBalance + account.getBalance());
            }
        }
    }

    public void updateWithdrawBalance(Account account) {
        for (Account accountFromList : accounts) {
            if (accountFromList.getAccountNumber().equals(account.getAccountNumber())) {
                double oldBalance = accountFromList.getBalance();
                accountFromList.setBalance(oldBalance - account.getBalance());
            }
        }
    }
}