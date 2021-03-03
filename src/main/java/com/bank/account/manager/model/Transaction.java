package com.bank.account.manager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Transaction {
    //private long id;
  //  private User user;
    private String type;
    private LocalDate date;
    private double value;
    private String accountNumber;


    public Transaction(String type, LocalDate date, double value, String accountNumber) {
        this.type = type;
        this.date = date;
        this.value = value;
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.value, value) == 0 &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date) &&
                Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, value, accountNumber);
    }

    @Override
    public String toString() {
        return "Transaction type = '" + type +
                ", Date= " + date +
                ", Value= " + value +
                ", Account Number= '" + accountNumber  +
                '}'+ "\n";
    }
}
