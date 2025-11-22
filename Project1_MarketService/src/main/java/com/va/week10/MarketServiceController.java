package com.va.week10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketServiceController {
	
    @Autowired
    private Environment environment;

    @Autowired
    private MarketService marketService;
	 
    @GetMapping("/")
    public String health() {
        return "Market Service is running";
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
     * Order / other services can call this endpoint to place a market order.
     */
    @PostMapping("/market/place")
    public Market placeMarketOrder(@RequestBody Market marketRequest) {
        System.out.println("Received request to place market order for symbol: "
                           + marketRequest.getStockSymbol());
        return marketService.placeOrder(marketRequest);
    }

    /*
     * Note for later (Jackson example from your professor's comment):
     *
     * You could use ObjectMapper like:
     *
     * ObjectMapper objectMapper = new ObjectMapper();
     * Market m = objectMapper.readValue(new URL("http://localhost:8081/market/place"), Market.class);
     *
     * or with a JSON file:
     *
     * Market m = objectMapper.readValue(new File("src/test/resources/market.json"), Market.class);
     */
}
