package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;

import java.util.List;
import java.util.Scanner;

public class Transaction {
    private static final Scanner scanner = new Scanner(System.in);
    private AccountService accountService = new AccountService();
    private List<Account> accounts = accountService.getAllAccounts();


    public void deposit() {
        System.out.println("Enter an account number");
        String accNumber = scanner.next();
        System.out.println("Enter a deposit amount");
        double amount = scanner.nextDouble();
        Account account = new Account();
        account.setAccountNumber(accNumber);
        account.setBalance(amount);
        accountService.updateDepositBalance(account);
        System.out.println("New balance: " + accountService.getAccountByAccNumber(account));

    }


    public void withdraw() {
        System.out.println("Enter a account number");
        String accNumber = scanner.next();
        System.out.println("Enter a withdraw amount");
        double amount = scanner.nextDouble();
        Account account = new Account();
        account.setAccountNumber(accNumber);
        account.setBalance(amount);
        accountService.updateWithdrawBalance(account);
        System.out.println("New balance: " + accountService.getAccountByAccNumber(account));
    }

    public void transferTo() {
        System.out.println("Enter the account number from which we withdraw the money: ");
        String accNumberFrom = scanner.next();
        System.out.println("Enter the account number from which we deposit the money: ");
        String accNumberTo = scanner.next();
        System.out.println("Enter the amount for transfer: ");
        double amount = scanner.nextDouble();
        Account accountFrom = new Account();
        Account accountTo = new Account();
        accountFrom.setAccountNumber(accNumberFrom);
        accountFrom.setBalance(amount);
        accountTo.setAccountNumber(accNumberTo);
        accountTo.setBalance(amount);
        accountService.updateWithdrawBalance(accountFrom);
        accountService.updateDepositBalance(accountTo);
        System.out.println("New balance: " + accountService.getAccountByAccNumber(accountFrom));
        System.out.println("New balance: " + accountService.getAccountByAccNumber(accountTo));
    }
}
