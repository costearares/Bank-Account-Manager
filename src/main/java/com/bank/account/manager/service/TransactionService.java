package com.bank.account.manager.service;

import com.bank.account.manager.dao.TransactionDAO;
import com.bank.account.manager.exception.AccountNotFoundException;
import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.Transaction;
import com.bank.account.manager.model.User;
import com.bank.account.manager.util.TransactionType;

import java.sql.SQLException;
import java.util.*;

public class TransactionService {

    private static final Scanner scanner = new Scanner(System.in);
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final AccountService accountService = new AccountService();


    public List<Transaction> getAllTransactions() throws SQLException {
        return transactionDAO.getTransactions();
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

    public void transferTo(User user) throws SQLException {
        List<Account> accounts = accountService.getAccountsByUserID(user);

        System.out.println("\n Available accounts for user " + user.getUsername() + ": ");
        System.out.println("--------------------------------------------------------------------");
        accounts.forEach(account ->
                System.out.println("Account Number: " + account.getAccountNumber()
                        + " :: Balance: " + account.getBalance()
                        + " :: Currency: " + account.getCurrency()
                ));
        System.out.println("--------------------------------------------------------------------");

        System.out.println("Enter the account number from which we withdraw the money: ");
        String accNumberFrom = scanner.next();
        System.out.println("Enter the account number from which we deposit the money: ");
        String accNumberTo = scanner.next();
        System.out.println("Enter the amount for transfer: ");
        double amount = scanner.nextDouble();

        Account accountFrom = new Account();
        accountFrom.setAccountNumber(accNumberFrom);
        accountFrom.setBalance(-amount);
        validateAccount(accountFrom);

        Account accountTo = new Account();
        accountTo.setAccountNumber(accNumberTo);
        accountTo.setBalance(amount);
        validateAccount(accountTo);

        if (accountFrom.getCurrency().equals(accountTo.getCurrency())) {
            accountService.updateBalance(accountFrom);
            System.out.println("New balance: " + accountService.getAccountByAccNumber(accountFrom));
            accountService.updateBalance(accountTo);
            System.out.println("New balance: " + accountService.getAccountByAccNumber(accountTo));

            Transaction transactionFrom = new Transaction();
            transactionFrom.setAccountNumber(accountFrom.getAccountNumber());
            transactionFrom.setAmount(amount);
            transactionFrom.setType(TransactionType.WITHDRAW);

            Transaction transactionTo = new Transaction();
            transactionTo.setAccountNumber(accountTo.getAccountNumber());
            transactionTo.setAmount(amount);
            transactionTo.setType(TransactionType.DEPOSIT);

            int updatedRowFrom = transactionDAO.insertTransaction(transactionFrom);
            int updatedRowTo = transactionDAO.insertTransaction(transactionTo);
            if (updatedRowFrom > 0 && updatedRowTo > 0) {
                System.out.println("Transfer successful!");
            } else {
                throw new IllegalArgumentException("Transfer failed!");
            }
        } else {
            throw new IllegalArgumentException("Accounts should have same currency type for transfer.");
        }
    }

    private void validateAccount(Account account) throws SQLException {
        Account dbAccount = accountService.getAccountByAccNumber(account);
        if (dbAccount == null) {
            throw new AccountNotFoundException("Account " + account.getAccountNumber() + " not found.");
        }
        account.setCurrency(dbAccount.getCurrency());
    }

}
