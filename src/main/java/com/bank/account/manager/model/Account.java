package com.bank.account.manager.model;

import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.util.Objects;

public class Account {
    private long id;
    private String accountNumber;
    private double balance;
    private Currency currency;
    private Type type;

    public Account(){

    }
    public Account(long id, String accountNumber, double balance, Currency currency, Type type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.type = type;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Double.compare(account.balance, balance) == 0 &&
                Objects.equals(accountNumber, account.accountNumber) &&
                currency == account.currency &&
                type == account.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance, currency, type);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", type=" + type +
                '}';
    }
}
