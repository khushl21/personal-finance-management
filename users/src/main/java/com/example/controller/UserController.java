package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @PutMapping("/{accountnumber}/{amount}")
    public User updateBalance(@PathVariable String accountnumber, @PathVariable double amount) {
        return userService.updateBalance(accountnumber, amount);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam String accountNumber) {
        return userService.getUserByAccountNumber(accountNumber)
                .map(User::getBalance)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
