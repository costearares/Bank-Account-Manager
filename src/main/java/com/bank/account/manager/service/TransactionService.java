package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransactionService {
    private static final Scanner scanner = new Scanner(System.in);
    private AccountService accountService = new AccountService();
    //private List<Account> accounts = accountService.getAllAccounts();
    private List<Transaction> transactions = new ArrayList<>();
    public LocalDate date = LocalDate.now();


    public void getAllTransactions() {
        System.out.println("Transactions: " + "\n" + transactions.toString());
    }

    public Transaction transactionWithHighestValue() {
        Transaction highestTransaction = null;
        for (Transaction value : transactions) {
            if (value.getValue() > highestTransaction.getValue()) {
                highestTransaction = value;
            }
        }
        System.out.println("highestTransaction :  " + highestTransaction);
        return highestTransaction;
    }

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
        String transactionType = "Deposit ";
        Transaction trans = new Transaction(transactionType, date, amount, account.getAccountNumber());
        transactions.add(trans);
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
        String transactionType = "Withdraw";
        Transaction trans = new Transaction(transactionType, date, amount, account.getAccountNumber());
        transactions.add(trans);
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
        String transactionTypeFrom = "Transfer from ";
        String transactionTypeTo = "Transfer to ";
        Transaction trans1 = new Transaction(transactionTypeFrom, date, amount, accountFrom.getAccountNumber());
        Transaction trans2 = new Transaction(transactionTypeTo, date, amount, accountTo.getAccountNumber());
        transactions.add(trans1);
        transactions.add(trans2);
    }

    public List<Transaction> moneyMoved(LocalDate dateFrom, LocalDate dateTo) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(dateFrom) && transaction.getDate().isBefore(dateTo)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }
}
