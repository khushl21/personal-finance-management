package com.example.controller;

import com.example.entity.Budget;
import com.example.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public Budget setBudget(@RequestBody Budget budget) {
        return budgetService.setBudget(budget);
    }

    @GetMapping("/{accountNumber}")
    public Optional<Budget> getBudgetByAccountNumber(@PathVariable String accountNumber) {
        return budgetService.getBudgetByAccountNumber(accountNumber);
    }

    @GetMapping("/{accountNumber}/warning")
    public String checkBudgetWarning(@PathVariable String accountNumber) {
        return budgetService.checkBudgetWarning(accountNumber);
    }
}
