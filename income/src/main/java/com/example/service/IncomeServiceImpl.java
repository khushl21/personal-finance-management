package com.example.service;

import com.example.entity.Income;
import com.example.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String API_GATEWAY_URL = "http://localhost:9090";

    @Override
    public Income createIncome(Income income) {
        // Step 1: Get User Balance
        double userBalance = getUserBalance(income.getAccountNumber());

        // Step 2: Add income to user balance
        updateUserBalance(income.getAccountNumber(), userBalance + income.getAmount());

        // Step 3: Save the income record
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

    // Helper method to fetch user balance
    private Double getUserBalance(String accountNumber) {
        return restTemplate.getForObject(
                API_GATEWAY_URL + "/users/balance?accountNumber=" + accountNumber, Double.class
        );
    }

    // Helper method to update user balance
    private void updateUserBalance(String accountNumber, double newBalance) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        restTemplate.exchange(
                API_GATEWAY_URL + "/users/" + accountNumber + "/" + newBalance,
                HttpMethod.PUT,
                requestEntity,
                Void.class
        );
    }
}
