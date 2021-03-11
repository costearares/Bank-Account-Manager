package com.bank.account.manager.model;

import com.bank.account.manager.util.TransactionType;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private long id;
    private String accountNumber;
    private TransactionType transactionType;
    private double amount;
    private LocalDate date;

    public Transaction() {
    }

    public Transaction(long id, String accountNumber, TransactionType transactionType, double amount, LocalDate date) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return transactionType;
    }

    public void setType(TransactionType accountType) {
        this.transactionType = accountType;
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
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(date, that.date) &&
                Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionType, date, amount, accountNumber);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountType='" + transactionType + '\'' +
                ", date=" + date +
                ", value=" + amount +
                ", accountNumber='" + accountNumber + '\'' +
                "} \n";
    }
}
