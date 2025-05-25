package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;

    public Income() {
        this.timestamp = LocalDateTime.now();
    }

    public Income(String accountNumber, String source, double amount) {
        this.accountNumber = accountNumber;
        this.source = source;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public String getSource() { return source; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setId(Long id) { this.id = id; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public void setSource(String source) { this.source = source; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
