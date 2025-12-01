package com.va.week10;

/**
 * DTO used to receive data from OrderService.
 * Fields map from Order:
 * - tickerSymbol        -> tickerSymbol
 * - quantityOrVolume    -> volume (later)
 * - price               -> quotePrice (later)
 */
public class MarketRequest {

    private String tickerSymbol;
    private int quantityOrVolume;
    private double price;

    public MarketRequest() {
    }

    public MarketRequest(String tickerSymbol, int quantityOrVolume, double price) {
        this.tickerSymbol = tickerSymbol;
        this.quantityOrVolume = quantityOrVolume;
        this.price = price;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
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
