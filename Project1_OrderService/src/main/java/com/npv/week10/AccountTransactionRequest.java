package com.npv.week10;

public class AccountTransactionRequest {

    private String accountNo;
    private String tickerSymbol;
    private String transactionDate;
    private String transactionTime;
    private int quantityOrVolume;
    private double price;

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
}
