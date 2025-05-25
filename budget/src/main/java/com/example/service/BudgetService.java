package com.example.service;

import com.example.entity.Budget;

import java.util.Optional;

public interface BudgetService {
    Budget setBudget(Budget budget);
    Optional<Budget> getBudgetByAccountNumber(String accountNumber);
    String checkBudgetWarning(String accountNumber);
}
