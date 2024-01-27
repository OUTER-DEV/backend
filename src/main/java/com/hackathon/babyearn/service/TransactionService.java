package com.hackathon.babyearn.service;


import com.hackathon.babyearn.model.Transaction;
import com.hackathon.babyearn.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactionByUserId(Long userId){
        return transactionRepository.findAllByUser_Id(userId);
    }



    //public List<TransactionEntity> getTransactionByUserId(Long userId, Date start,Date End){
      //  return transactionRepository.findAllByUser_Id(userId);
    //}

}
