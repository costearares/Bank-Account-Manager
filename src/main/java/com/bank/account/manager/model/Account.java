package com.bank.account.manager.model;

import com.bank.account.manager.util.AccountType;
import com.bank.account.manager.util.Currency;

import java.util.Objects;

public class Account {

    private long id;
    private String accountNumber;
    private double balance;
    private Currency currency;
    private AccountType accountType;
    private long userId;

    public Account() {
    }

    public Account(long id, String accountNumber, double balance, Currency currency, AccountType accountType, long userId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.accountType = accountType;
        this.userId = userId;
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

    public AccountType getType() {
        return accountType;
    }

    public void setType(AccountType accountType) {
        this.accountType = accountType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", accountType=" + accountType +
                ", user_id=" + userId +
                "} \n";
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
                accountType == account.accountType &&
                Objects.equals(userId, account.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance, currency, accountType, userId);
    }
}
