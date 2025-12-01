package com.npv.week10;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private Environment environment;

    @Autowired
    private OrderService orderService;

    @GetMapping("/health")
    public String health() {
        String port = environment.getProperty("local.server.port");
        return "Order Service is running on port: " + port;
    }

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order orderRequest) {
        System.out.println("Received order for account: " + orderRequest.getAccountNo()
                + " ticker: " + orderRequest.getTickerSymbol());
        return orderService.placeOrder(orderRequest);
    }
    
    @GetMapping
    public List<Order> getAllOrdersRest() {
        return orderService.getAllOrders();
    }
}
