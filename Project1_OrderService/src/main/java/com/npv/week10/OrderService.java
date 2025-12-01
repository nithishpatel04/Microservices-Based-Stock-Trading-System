package com.npv.week10;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;   // Bean defined in your configuration

    /**
     * Main business method:
     *  - calculate total amount
     *  - save order in MongoDB
     *  - call MarketService to place market order
     *  - if executed, call AccountTransactionService
     *  - update status (PLACED / EXECUTED)
     */
    public Order placeOrder(Order orderRequest) {

        // 1) Calculate total amount
        double total = orderRequest.getQuantityOrVolume() * orderRequest.getPrice();

        // 2) Build Order object to save
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setAccountNo(orderRequest.getAccountNo());
        order.setTickerSymbol(orderRequest.getTickerSymbol());
        order.setTransactionDate(orderRequest.getTransactionDate());
        order.setTransactionTime(orderRequest.getTransactionTime());
        order.setQuantityOrVolume(orderRequest.getQuantityOrVolume());
        order.setPrice(orderRequest.getPrice());
        order.setTotalAmount(total);

        // default status before calling MarketService
        order.setStatus("PLACED");

        // 3) Save initial order
        Order savedOrder = orderRepository.save(order);

        // 4) Build request for MarketService
        MarketRequest marketRequest = new MarketRequest(
                savedOrder.getTickerSymbol(),
                savedOrder.getQuantityOrVolume(),
                savedOrder.getPrice()
        );

        // 5) Call MarketService
        try {
            restTemplate.postForObject(
                    // if using Eureka: "http://market-service/market/place"
                    "http://localhost:9090/market/place",   // MarketService endpoint
                    marketRequest,
                    String.class
            );

            // If call succeeds → mark as EXECUTED
            savedOrder.setStatus("EXECUTED");

            // 6) If executed, notify Account-Transaction Service
            notifyAccountTransactionService(savedOrder);

        } catch (Exception ex) {
            System.out.println("Error calling MarketService: " + ex.getMessage());
            // If MarketService fails, keep status as PLACED for now
        }

        // 7) Save updated status and return final Order
        return orderRepository.save(savedOrder);
    }

    /**
     * Fetch all orders for the list page.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ================= NEW METHOD ADDED BELOW =================

    /**
     * Calls Account-Transaction-Service to record the transaction
     * for this executed order.
     */
    private void notifyAccountTransactionService(Order savedOrder) {
        try {
            AccountTransactionRequest txReq = new AccountTransactionRequest();
            txReq.setAccountNo(savedOrder.getAccountNo());
            txReq.setTickerSymbol(savedOrder.getTickerSymbol());
            txReq.setTransactionDate(savedOrder.getTransactionDate());
            txReq.setTransactionTime(savedOrder.getTransactionTime());
            txReq.setQuantityOrVolume(savedOrder.getQuantityOrVolume());
            txReq.setPrice(savedOrder.getPrice());

            restTemplate.postForObject(
                    // if using Eureka: "http://account-transaction-service/accounts/transactions"
                    "http://localhost:9091/accounts/transactions",   // AccountTransactionService endpoint
                    txReq,
                    Void.class
            );

            System.out.println("Transaction recorded for account: " + savedOrder.getAccountNo());
        } catch (Exception e) {
            System.out.println("Error calling Account-Transaction-Service: " + e.getMessage());
            // For this project, logging is enough
        }
    }
}
