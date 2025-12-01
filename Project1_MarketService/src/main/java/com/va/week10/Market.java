package com.va.week10;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "market_orders")
public class Market {

    @Id
    private String id;

    // professor's attributes (camelCase version)
    private String tickerSymbol;   // ticker_symbol
    private String stockName;      // stock_name
    private double quotePrice;     // quote_price
    private double openPrice;      // open_price
    private double closePrice;     // close_price
    private long volume;           // volume
    private double bid;            // bid
    private double ask;            // ask
    private String exchange;       // exchange

    // extras for traceability
    private LocalDateTime createdAt;

    public Market() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getStockName() {
        return stockName;
    }
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getQuotePrice() {
        return quotePrice;
    }
    public void setQuotePrice(double quotePrice) {
        this.quotePrice = quotePrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }
    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }
    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public long getVolume() {
        return volume;
    }
    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getBid() {
        return bid;
    }
    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }
    public void setAsk(double ask) {
        this.ask = ask;
    }

    public String getExchange() {
        return exchange;
    }
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
