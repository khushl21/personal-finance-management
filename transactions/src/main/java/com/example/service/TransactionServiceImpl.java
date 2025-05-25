package com.example.service;

import com.example.entity.Transaction;
import com.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

//    private final String USER_SERVICE_URL = "http://localhost:8081/users"; // User Service Base URL

    private final String API_GATEWAY_URL = "http://localhost:9090";

    @Override
    public Transaction createTransaction(Transaction transaction) {
        // Step 1: Retrieve Sender and Receiver Balances
        double senderBalance = getUserBalance(transaction.getSenderAccountNumber());
        double receiverBalance = getUserBalance(transaction.getReceiverAccountNumber());

        // Step 2: Check Sufficient Balance
        if (senderBalance < transaction.getAmount()) {
            throw new RuntimeException("Insufficient balance!");
        }

        // Step 3: Deduct amount from sender
        updateUserBalance(transaction.getSenderAccountNumber(), senderBalance - transaction.getAmount());

        // Step 4: Add amount to receiver
        updateUserBalance(transaction.getReceiverAccountNumber(), receiverBalance + transaction.getAmount());

        // Step 5: Save Transaction
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Helper method to fetch balance by account number
    private Double getUserBalance(String accountNumber) {
        return restTemplate.getForObject(
                API_GATEWAY_URL + "/users/balance?accountNumber=" + accountNumber, Double.class
        );
    }

    // Helper method to update user balance
    private void updateUserBalance(String accountNumber, double newBalance) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        restTemplate.exchange(
                API_GATEWAY_URL + "/users/" + accountNumber + "/" + newBalance,
                HttpMethod.PUT,
                requestEntity,
                Void.class
        );
    }
}
