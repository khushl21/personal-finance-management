package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private double budgetLimit;

    public Budget() {
    }

    public Budget(String accountNumber, double budgetLimit) {
        this.accountNumber = accountNumber;
        this.budgetLimit = budgetLimit;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public double getBudgetLimit() { return budgetLimit; }

    public void setId(Long id) { this.id = id; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public void setBudgetLimit(double budgetLimit) { this.budgetLimit = budgetLimit; }
}
