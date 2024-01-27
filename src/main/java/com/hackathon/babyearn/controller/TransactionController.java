package com.hackathon.babyearn.controller;


import com.hackathon.babyearn.controller.mapper.TransactionMapper;
import com.hackathon.babyearn.model.Transaction;
import com.hackathon.babyearn.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionController {
    private final TransactionMapper transactionMapper;
    private final TransactionService transactionService;



     @GetMapping("")
    public List<Transaction> getAllTransaction(Long userId){
        return transactionService.getTransactionByUserId(userId);
    }
}
