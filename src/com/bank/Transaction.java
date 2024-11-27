package com.bank;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String transactionType;
    private double amount;
    private String date;

    public Transaction(String transactionType, double amount, String date) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return transactionType + " of $" + amount + " on " + date;
    }
}
