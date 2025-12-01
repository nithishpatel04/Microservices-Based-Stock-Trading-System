package com.npv.week10;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountTransactionController {

    @Autowired
    private Environment environment;

    @Autowired
    private AccountTransactionService txService;

    @GetMapping("/health")
    public String health() {
        String port = environment.getProperty("local.server.port");
        return "Account-Transaction Service running on port: " + port;
    }

    /**
     * Main operation:
     * record a transaction for an account.
     */
    @PostMapping("/transactions")
    public AccountTransaction createTransaction(@RequestBody AccountTransaction request) {
        System.out.println("Recording transaction for account: " + request.getAccountNo()
                + " ticker: " + request.getTickerSymbol());
        return txService.recordTransaction(request);
    }

    /**
     * For demo: list all account transactions.
     */
    @GetMapping("/transactions")
    public List<AccountTransaction> listAllTransactions() {
        return txService.getAll();
    }
}
