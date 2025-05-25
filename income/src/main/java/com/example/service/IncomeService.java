package com.example.service;

import com.example.entity.Income;

import java.util.List;

public interface IncomeService {
    Income createIncome(Income income);
    List<Income> getAllIncome();
}
