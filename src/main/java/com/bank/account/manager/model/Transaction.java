package com.bank.account.manager.model;

import com.bank.account.manager.util.TransactionType;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private long id;
    private String accountNumber;
    private double amount;
    private TransactionType type;
    private LocalDate date;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date) &&
                Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, date, amount, accountNumber);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", value=" + amount +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
