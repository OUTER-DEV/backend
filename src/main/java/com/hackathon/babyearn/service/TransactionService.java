package com.hackathon.babyearn.service;


import com.hackathon.babyearn.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

}
