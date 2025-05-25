package com.example.service;

import com.example.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
}
