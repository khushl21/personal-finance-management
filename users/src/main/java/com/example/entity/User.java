package com.example.entity;

import jakarta.persistence.*;

import java.util.Random;




@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private double balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User() {
        this.accountNumber = generateAccountNumber();
    }

    public User(String username, String password, String email, double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.accountNumber = generateAccountNumber();
    }

    private String generateAccountNumber() {
        return "ACC" + new Random().nextInt(1000000);
    }

    // Getters and Setters
}
