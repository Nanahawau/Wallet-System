package com.fgt.walletsystem.repositories;

import com.fgt.walletsystem.entities.Transaction;
import com.fgt.walletsystem.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction getTransactionsByPaystackReference(String reference);
    List<Transaction> getTransactionsByWallet(Wallet wallet);
}
