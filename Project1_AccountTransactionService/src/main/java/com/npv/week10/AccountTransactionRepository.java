package com.npv.week10;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends MongoRepository<AccountTransaction, String> {
}
