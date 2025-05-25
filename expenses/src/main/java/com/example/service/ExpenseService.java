package com.example.service;

import com.example.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(Expense expense);
    List<Expense> getAllExpenses();
}
