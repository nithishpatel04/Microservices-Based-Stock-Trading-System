package com.npv.week10;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_transactions")
public class AccountTransaction {

    @Id
    private String id;

    // professor's attributes (Account class)
    private String accountNo;
    private String tickerSymbol;
    private String transactionDate;
    private String transactionTime;
    private int quantityOrVolume;
    private double price;

    // derived + extra
    private double totalAmount;
    private String transactionType;   // e.g. "DEBIT", "CREDIT"

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }
    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getQuantityOrVolume() {
        return quantityOrVolume;
    }
    public void setQuantityOrVolume(int quantityOrVolume) {
        this.quantityOrVolume = quantityOrVolume;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
