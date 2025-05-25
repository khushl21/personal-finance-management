package com.example.controller;

import com.example.entity.Income;
import com.example.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public Income createIncome(@RequestBody Income income) {
        return incomeService.createIncome(income);
    }

    @GetMapping
    public List<Income> getAllIncome() {
        return incomeService.getAllIncome();
    }
}
