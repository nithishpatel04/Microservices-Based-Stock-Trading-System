package com.va.week10;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarketServiceController {
	
    @Autowired
    private Environment environment;

    @Autowired
    private MarketService marketService;
	 
    @GetMapping("/")
    public String health() {
        String port = environment.getProperty("local.server.port");
        return "Market Service is running on port: " + port;
    }
	 
    @GetMapping("/backend")
    public String backend() {
        System.out.println("Inside MarketServiceController::backend...");
        String serverPort = environment.getProperty("local.server.port");
        System.out.println("Port : " + serverPort);
        return "Hello from Market Backend! Host : localhost :: Port : " + serverPort;
    }

    /**
     * Main operation for this microservice:
     * OrderService (or Postman) posts here to execute a market order.
     */
    @PostMapping("/market/place")
    public Market placeMarketOrder(@RequestBody MarketRequest marketRequest) {
        System.out.println("Received request to place market order for ticker: "
                           + marketRequest.getTickerSymbol());
        return marketService.executeFromRequest(marketRequest);
    }

    /**
     * Optional: list all market orders for demo / testing.
     */
    @GetMapping("/market/orders")
    public List<Market> getAllMarketOrders() {
        return marketService.getAll();
    }
}
