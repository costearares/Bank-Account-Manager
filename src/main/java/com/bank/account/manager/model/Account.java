package com.bank.account.manager.model;

import com.bank.account.manager.util.Currency;
import com.bank.account.manager.util.Type;

import java.util.Objects;

public class Account {
    private long id;
    private String accountNumber;
    // private final long acctNum = ThreadLocalRandom.current().nextLong(100000000, 999999999);
    private double balance;
    private Currency currency;
    private Type type;
    private long user_id;

    public Account() {

    }

    public Account(long id, String accountNumber, double balance, Currency currency, Type type, long user_id) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.type = type;
        this.user_id = user_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", type=" + type +
                ", user_id=" + user_id +
                '}';
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
                type == account.type &&
                Objects.equals(user_id, account.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance, currency, type, user_id);
    }
}
