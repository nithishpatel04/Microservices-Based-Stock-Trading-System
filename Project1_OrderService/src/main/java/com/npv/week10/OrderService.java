package com.npv.week10;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Main business method:
     * - calculate total amount
     * - set status
     * - save in MongoDB
     */
    public Order placeOrder(Order orderRequest) {

        // calculate total
        double total = orderRequest.getQuantity() * orderRequest.getPricePerUnit();

        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(orderRequest.getUserId());
        order.setStockSymbol(orderRequest.getStockSymbol());
        order.setQuantity(orderRequest.getQuantity());
        order.setPricePerUnit(orderRequest.getPricePerUnit());
        order.setTotalAmount(total);
        order.setStatus("PLACED");

        // Save to MongoDB
        return orderRepository.save(order);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}

