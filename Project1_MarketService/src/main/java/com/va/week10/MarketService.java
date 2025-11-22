package com.va.week10;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class MarketService {

    /**
     * Simulates placing an order in the Market.
     * Later you can connect this to MongoDB or another microservice.
     */
    public Market placeOrder(Market request) {

        // Calculate total
        double totalAmount = request.getQuantity() * request.getPricePerUnit();

        // Build response object
        Market response = new Market();
        response.setId(UUID.randomUUID().toString());
        response.setStockSymbol(request.getStockSymbol());
        response.setQuantity(request.getQuantity());
        response.setPricePerUnit(request.getPricePerUnit());
        response.setTotalAmount(totalAmount);
        response.setStatus("EXECUTED");      // or "PLACED"
        response.setCreatedAt(LocalDateTime.now());

        // Here you would save to MongoDB repository in the future
        // marketRepository.save(response);

        return response;
    }
}
