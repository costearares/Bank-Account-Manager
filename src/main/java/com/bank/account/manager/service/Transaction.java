package com.bank.account.manager.service;

import com.bank.account.manager.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transaction {
    Scanner s = new Scanner(System.in);
    int numOfAccounts = 5;
    List<Account> accounts = new ArrayList<>();



    public void deposit() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a account id");
        long idd = s.nextLong();
        System.out.println("Enter a deposit amount");
        double da = s.nextDouble();
        for (Account a : accounts) {
            if (a.getId() == idd) {
                a.deposiT(da);
                System.out.println("New balance: " + a.getBalance());
            }
        }
    }

    public void withdraw() {
        System.out.println("Enter a account number");
        long idw = s.nextLong();
        System.out.println("Enter a withdraw amount");
        double wa = s.nextDouble();
        for (Account a : accounts) {
            if (a.getId() == idw) {
                double balance = a.getBalance() - wa;
                System.out.println("New balance: " + balance);
                break;
            } else {
                System.out.println("The amount is too big");
                break;
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

    public void transferTo() {
        System.out.println("Enter the id account from which we withdraw the money: ");
        long itw = s.nextLong();
        System.out.println("Enter the id account from which we deposit the money: ");
        long itd = s.nextLong();
        System.out.println("Enter the amount for transfer: ");
        double amt = s.nextDouble();
        Account from=getAccountByID(itw);
        Account to=getAccountByID(itd);
       if(from.getBalance()<amt){
           double balancefrom=from.getBalance();
           double balanceto= to.getBalance();
           balancefrom-=amt;
           balanceto+=amt;
           System.out.println("Transfer successful!");
       }else {
           System.out.println("Insufficient funds");
       }

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
