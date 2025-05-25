package com.example.service;

import com.example.entity.Expense;
import com.example.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String API_GATEWAY_URL = "http://localhost:9090";

    @Override
    public Expense createExpense(Expense expense) {
        // Step 1: Get User Balance
        double userBalance = getUserBalance(expense.getAccountNumber());

        if (userBalance < expense.getAmount()) {
            throw new RuntimeException("Insufficient balance!");
        }

        // Step 2: Deduct amount from user
        updateUserBalance(expense.getAccountNumber(), userBalance - expense.getAmount());

        // Step 3: Save the expense
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Helper method to fetch user balance
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
