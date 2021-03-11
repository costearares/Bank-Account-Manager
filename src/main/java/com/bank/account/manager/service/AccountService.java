package com.bank.account.manager.service;

import com.bank.account.manager.dao.AccountDAO;
import com.bank.account.manager.exception.AccountAlreadyExistsException;
import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.User;
import com.bank.account.manager.util.AccountType;
import com.bank.account.manager.util.Currency;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AccountService {

    private static final Scanner keyboard = new Scanner(System.in);

    private AccountDAO accountDAO = new AccountDAO();

    public List<Account> getAllAccounts() throws SQLException {
        return accountDAO.getAccounts();
    }

    public int openNewAccount(User user) throws SQLException {
        System.out.println("Enter an account number: ");
        String accountNumber = keyboard.next();
        System.out.println("Enter a opening balance: ");
        double balance = keyboard.nextDouble();

        Account account = new Account();
        account.setUserId(user.getId());
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setCurrency(getAccountCurrency());
        account.setType(getAccountType());

        Account dbAccount = accountDAO.getAccountByAccountNumber(account);
        if (dbAccount != null) {
            throw new AccountAlreadyExistsException("Account already exists!");
        }

        int rowsNo = accountDAO.insertAccount(account);
        if (rowsNo == 0) {
            throw new IllegalArgumentException("Account not inserted.");
        }
        System.out.println("The new account is: " + account);

        return rowsNo;
    }

    private AccountType getAccountType() {
        System.out.println("Choice account type: ");
        System.out.println("1: CURRENT");
        System.out.println("2: SAVINGS");
        String typeChoice = keyboard.next();
        AccountType accountType = null;
        if (typeChoice.equals("1")) {
            accountType = AccountType.CURRENT;
        } else if (typeChoice.equals("2")) {
            accountType = AccountType.SAVINGS;
        } else {
            System.out.println("Enter again!");
        }
        return accountType;
    }

    private Currency getAccountCurrency() {
        System.out.println("Choice currency: ");
        System.out.println("1: RON");
        System.out.println("2: EUR");
        String choice = keyboard.next();

        Currency accountCurrency = null;
        if (choice.equals("1")) {
            accountCurrency = Currency.RON;
        } else if (choice.equals("2")) {
            accountCurrency = Currency.EUR;
        } else {
            System.out.println("Enter again!");
        }
        return accountCurrency;
    }

    public Account getAccountByAccNumber(Account inputAccount) throws SQLException {
        for (Account account : accountDAO.getAccounts()) {
            if (account.getAccountNumber().equals(inputAccount.getAccountNumber())) {
                return account;
            }
        }
        return null;
    }

    public Account getAccountByUserID(Account account) throws SQLException {
        for (Account value : accountDAO.getAccounts()) {
            if (value.getUserId() == (account.getUserId())) {
                return value;
            }
        }
        return null;
    }

    public void updateBalance(Account account) throws SQLException {
        Account dbAccount = accountDAO.getAccountByAccountNumber(account);
        if (dbAccount.getAccountNumber().equals(account.getAccountNumber())) {
            double newBalance = dbAccount.getBalance() + account.getBalance();
            if (newBalance < 0) {
                throw new IllegalArgumentException("Insufficient founds");
            }
            dbAccount.setBalance(newBalance);
        }
        accountDAO.updateBalance(dbAccount);
    }

    public void deleteAccount() throws SQLException {
        System.out.println("Enter Account Number: ");
        String accountNumber = keyboard.next();

        Account account = new Account();
        account.setAccountNumber(accountNumber);

        accountDAO.deleteAccount(account);
    }

}