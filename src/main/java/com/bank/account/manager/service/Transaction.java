package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transaction {
    Scanner keyboard = new Scanner(System.in);
    List<Account> accounts = new ArrayList<>();

    /*public void deposit(double amount) {
        if (amount > 0) {
           double balance += amount;
            System.out.println("Amount deposited: " + amount);
            //balance -= FEE;
            System.out.println("Fee applied: " + FEE);
            System.out.println("Current balance is: " + balance);

        } else {
            System.out.println("A negative amount cannot be deposited");
        }

    }*/
    public void deposit(Account account, double amount){
        for (Account value : accounts) {
            if (value.getId() == account.getId()) {
                double balance = value.getBalance();
                balance += amount;
                System.out.println("Amount deposited successfully");
                return;
            }
        }
        System.out.println("Account number not found");

    }

    public void withdraw(Account account, double amount){
        for (Account value : accounts) {
            if (value.getId() == account.getId()) {
                double balance = value.getBalance();
                balance -= amount;
                System.out.println("Amount withdrawn  successfully");
                return;
            }
        }
        System.out.println("Account number not found");

    }

    public void transferTo(){

    }

    public void printAccountInfo(long idAccount) {
        for (Account account : accounts) {
            if (idAccount == account.getId()) {
                System.out.println(account.toString());
                return;
            }
        }
        System.out.println("Account number not found.");
    }
}
