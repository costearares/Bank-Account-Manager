package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;
import com.bank.account.manager.model.User;
import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountService {
    Scanner keyboard = new Scanner(System.in);
    List<Account> accounts=new ArrayList<>();


    public void getAllAccounts() {
        System.out.println("All accounts: "+ accounts.toString());
    }


    public void openNewAccount() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a account id: ");
        long nid = keyboard.nextLong();
        System.out.println("Enter a account number: ");
        String nan = keyboard.next();
        System.out.println("Enter a opening balance: ");
        double nb = keyboard.nextDouble();
        Account account = new Account(nid, nan, nb, Currency.valueOf("RON"), Type.valueOf("SAVINGS"));
        for (Account value : accounts) {
            if (value.getId() != account.getId()) {
                System.out.println("The new account is: " + account.toString());
                accounts.add(account);
                break;
            }
            else {
                System.out.println("This account already exists!");
            }
        }
    }
    public Account getAccountByID(long id) {
        Account acc = new Account();
        for (Account account : accounts) {
            if (account.getId() == id) {
                acc = account;
            }
        }
        return acc;
    }
    public void printBalance() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a account id: ");
        long id = keyboard.nextLong();
        for (Account a : accounts) {
            if (a.getId() == id) {
                System.out.println("Balance is: " + a.getBalance());
                break;
            } else {
                System.out.println("Account not found ");
            }
        }
    }


    public void removeAccount(Account account) {
        accounts.remove(account);

    }

    public void updateAccount(Account account) {


    }


}