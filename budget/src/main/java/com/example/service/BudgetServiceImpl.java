package com.example.service;

import com.example.entity.Budget;
import com.example.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String API_GATEWAY_URL = "http://localhost:9090";

    @Override
    public Budget setBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public Optional<Budget> getBudgetByAccountNumber(String accountNumber) {
        return budgetRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public String checkBudgetWarning(String accountNumber) {
        // Step 1: Get User Balance
        double userBalance = getUserBalance(accountNumber);

        // Step 2: Get Budget Limit
        Optional<Budget> budget = budgetRepository.findByAccountNumber(accountNumber);
        if (budget.isEmpty()) {
            return "No budget set for this account.";
        }

        double budgetLimit = budget.get().getBudgetLimit();

        // Step 3: Check if balance is close to the budget (90% warning threshold)
        if (userBalance <= budgetLimit * 1.1) {
            return "⚠️ Warning: Your balance is approaching your budget limit!";
        }
        return "Your balance is within safe limits.";
    }

    // Helper method to fetch user balance
    private Double getUserBalance(String accountNumber) {
        ResponseEntity<Double> response = restTemplate.getForEntity(
                API_GATEWAY_URL + "/users/balance?accountNumber=" + accountNumber, Double.class
        );
        return response.getBody();
    }
}
