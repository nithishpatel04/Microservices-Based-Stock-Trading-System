package com.npv.week10;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionService {

    @Autowired
    private AccountTransactionRepository repo;

    /**
     * Main business method:
     * - calculate totalAmount
     * - set transactionType
     * - save in MongoDB
     */
    public AccountTransaction recordTransaction(AccountTransaction request) {

        double total = request.getQuantityOrVolume() * request.getPrice();

        AccountTransaction tx = new AccountTransaction();
        tx.setId(UUID.randomUUID().toString());
        tx.setAccountNo(request.getAccountNo());
        tx.setTickerSymbol(request.getTickerSymbol());
        tx.setTransactionDate(request.getTransactionDate());
        tx.setTransactionTime(request.getTransactionTime());
        tx.setQuantityOrVolume(request.getQuantityOrVolume());
        tx.setPrice(request.getPrice());
        tx.setTotalAmount(total);

        // For demo: mark all as DEBIT (buying stock)
        tx.setTransactionType("DEBIT");

        return repo.save(tx);
    }

    public List<AccountTransaction> getAll() {
        return repo.findAll();
    }
}
