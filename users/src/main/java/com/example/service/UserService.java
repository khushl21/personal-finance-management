package com.example.service;

import com.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User updateBalance(String accountnumber, double amount);
    Optional<User> getUserByAccountNumber(String accountNumber);
}
