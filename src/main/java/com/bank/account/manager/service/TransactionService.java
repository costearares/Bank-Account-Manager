package com.bank.account.manager.service;

import com.bank.account.manager.dao.AccountDAO;
import com.bank.account.manager.dao.TransactionDAO;
import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.Transaction;
import com.bank.account.manager.util.TransactionType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


public class TransactionService {
    private static final Scanner scanner = new Scanner(System.in);
    private final AccountService accountService = new AccountService();
    private final TransactionDAO transactionDAO = new TransactionDAO();


    public void getAllTransactions() throws SQLException {
        System.out.println("Transactions: " + "\n" + transactionDAO.getTransactions().toString());
    }

    public Transaction transactionWithHighestValue() throws SQLException {
        Transaction highestTransaction = null;
        for (Transaction value : transactionDAO.getTransactions()) {
            if (value.getAmount() > highestTransaction.getAmount()) {
                highestTransaction = value;
            }
        }
        System.out.println("highestTransaction :  " + highestTransaction);
        return highestTransaction;
    }

    public void moveMoney(TransactionType type) throws SQLException {
        System.out.println("Enter a account number: ");
        String accNumber = scanner.next();
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        amount = Math.abs(amount);

        if (type.equals(TransactionType.WITHDRAW)) {
            amount = -amount;
        }
        Account account = new Account();
        account.setAccountNumber(accNumber);
        account.setBalance(amount);

        accountService.updateBalance(account);
        System.out.println("New balance: " + accountService.getAccountByAccNumber(account));

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(account.getAccountNumber());
        transaction.setAmount(amount);
        transaction.setType(type);

        int numberOfUpdatedRows = transactionDAO.insertTransaction(transaction);

        if (numberOfUpdatedRows > 0) {
            System.out.println(type + " is successful!");
        } else {
            System.out.println("The account number is wrong!");
        }
    }

    public void transferTo() throws SQLException {
        System.out.println("Enter the account number from which we withdraw the money: ");
        String accNumberFrom = scanner.next();
        System.out.println("Enter the account number from which we deposit the money: ");
        String accNumberTo = scanner.next();
        System.out.println("Enter the amount for transfer: ");
        double amount = scanner.nextDouble();

        Account accountFrom = new Account();
        accountFrom.setAccountNumber(accNumberFrom);
        accountFrom.setBalance(-amount);

        Account accountTo = new Account();
        accountTo.setAccountNumber(accNumberTo);
        accountTo.setBalance(amount);

        accountService.updateBalance(accountFrom);
        System.out.println("New balance: " + accountService.getAccountByAccNumber(accountFrom));
        accountService.updateBalance(accountTo);
        System.out.println("New balance: " + accountService.getAccountByAccNumber(accountTo));

        Transaction transactionFrom = new Transaction();
        transactionFrom.setAccountNumber(accountFrom.getAccountNumber());
        transactionFrom.setAmount(amount);
        transactionFrom.setType(TransactionType.WITHDRAW);

        Transaction transactionTo = new Transaction();
        transactionTo.setAccountNumber(accountFrom.getAccountNumber());
        transactionTo.setAmount(amount);
        transactionTo.setType(TransactionType.DEPOSIT);

        int updatedRowFrom = transactionDAO.insertTransaction(transactionFrom);
        int updatedRowTo = transactionDAO.insertTransaction(transactionTo);
        if (updatedRowFrom > 0 && updatedRowFrom > 0) {
            System.out.println(" Transfer successful!");
        } else {
            System.out.println("The account number is wrong!");
        }
    }


    public List<Transaction> moneyMoved(LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : transactionDAO.getTransactions()) {
            if (transaction.getDate().isAfter(dateFrom) && transaction.getDate().isBefore(dateTo)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }
}
