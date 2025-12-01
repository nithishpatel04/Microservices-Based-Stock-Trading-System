package com.va.week10;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

    @Autowired
    private MarketRepository marketRepository;

    /**
     * Called when OrderService (or Postman) sends a MarketRequest.
     * We:
     *  - map request to Market entity
     *  - set quotePrice, volume, etc.
     *  - save in MongoDB
     */
    public Market executeFromRequest(MarketRequest req) {

        Market m = new Market();
        m.setId(UUID.randomUUID().toString());

        m.setTickerSymbol(req.getTickerSymbol());
        m.setVolume(req.getQuantityOrVolume());     // quantityorvolume -> volume
        m.setQuotePrice(req.getPrice());           // price -> quote_price

        // simple defaults for demo
        m.setStockName("NA");                      // could be looked up later
        m.setOpenPrice(req.getPrice());
        m.setClosePrice(req.getPrice());
        m.setBid(req.getPrice() - 0.1);
        m.setAsk(req.getPrice() + 0.1);
        m.setExchange("TSX");                      // or any example exchange

        m.setCreatedAt(LocalDateTime.now());

        return marketRepository.save(m);
    }

    /**
     * Helper to list all market orders (for demo).
     */
    public List<Market> getAll() {
        return marketRepository.findAll();
    }
}
