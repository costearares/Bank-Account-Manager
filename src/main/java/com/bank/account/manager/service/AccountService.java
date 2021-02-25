package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountService {

    List<Account> accounts = new ArrayList<>();

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        for (Account value : accounts) {
            if (value.getId()!=account.getId()) {
                accounts.add(account);
            } else {
                System.out.println("This account already exists!");
            }
        }
    }

    public void removeAccount(Account account) {
        accounts.remove(account);

    }

    public void updateAccount(Account account) {

    }
}
