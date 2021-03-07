package com.bank.account.manager.service;

import com.bank.account.manager.dao.AccountDAO;
import com.bank.account.manager.model.Account;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AccountService {
    private static final Scanner keyboard = new Scanner(System.in);
    private AccountDAO accountDAO = new AccountDAO();

    public List<Account> getAllAccounts() throws SQLException {
        return accountDAO.getAccounts();
    }

    public Currency selectAccountCurrency() {
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

    public Type selectAccountType() {
        System.out.println("Choice account type: ");
        System.out.println("1: CURRENT");
        System.out.println("2: SAVINGS");
        String typeChoice = keyboard.next();
        Type accountType = null;
        if (typeChoice.equals("1")) {
            accountType = Type.CURRENT;
        } else if (typeChoice.equals("2")) {
            accountType = Type.SAVINGS;
        } else {
            System.out.println("Enter again!");
        }
        return accountType;
    }

    public int openNewAccount() throws SQLException {

        System.out.println("Enter an account number: ");
        String accountNumber = keyboard.next();
        System.out.println("Enter a opening balance: ");
        double balance = keyboard.nextDouble();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setCurrency(selectAccountCurrency());
        account.setType(selectAccountType());
        Account dbAccount = accountDAO.getAccountByAccountNumber(account);
        if (dbAccount != null) {
            //throw ex
        }
        int rowsNo = accountDAO.insertAccount(account);
        System.out.println("The new account is: " + account);
        if (rowsNo == 0) {
            //throw e
        }
        System.out.println(rowsNo);

        return rowsNo;
    }

    /*public Account getAccountByID(String accountNumber) throws SQLException {

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        Account dbAccount = accountDAO.getAccountByAccountNumber(account);

        for (Account value : accountDAO.getAccounts()) {
            if (value.getAccountNumber().equals(accountNumber)) {
                account = value;
            }
        }
        return dbAccount;
    }*/

    public Account getAccountByAccNumber(Account inputAccount) throws SQLException {
        for (Account account : accountDAO.getAccounts()) {
            if (account.getAccountNumber().equals(inputAccount.getAccountNumber())) {
                return account;
            }
        }
        return null;
    }

    public void printBalance() throws SQLException {
        System.out.println("Enter an account id: ");
        long id = keyboard.nextLong();
        for (Account value : accountDAO.getAccounts()) {
            if (value.getId() == id) {
                System.out.println("Balance is: " + value.getBalance());
                break;
            } else {
                System.out.println("Account not found ");
            }
        }
    }


    public void deleteAccount() throws SQLException {
        System.out.println("Enter Account Number: ");
        String accountNumber = keyboard.next();
        accountDAO.deleteAccount(accountNumber);

    }


    public void updateBalance(Account account) throws SQLException {
        Account dbAccount = accountDAO.getAccountByAccountNumber(account);
        if (dbAccount.getAccountNumber().equals(account.getAccountNumber())) {
            double newBalance = dbAccount.getBalance() + account.getBalance();
            dbAccount.setBalance(newBalance);
        }
        accountDAO.updateBalance(dbAccount);
    }


    public double totalValueOfAccounts() throws SQLException {
        double total = 0.0;
        for (Account value : accountDAO.getAccounts()) {
            total += value.getBalance();
        }
        System.out.println("The value of total accounts is: " + total);
        return total;
    }
}