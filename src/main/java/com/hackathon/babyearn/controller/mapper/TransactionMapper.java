package com.hackathon.babyearn.controller.mapper;


import com.hackathon.babyearn.model.Transaction;
import com.hackathon.babyearn.repository.UserRepository;
import com.hackathon.babyearn.repository.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TransactionMapper {

    private final UserRepository userRepository;
    public TransactionEntity toRest (Transaction transaction){
        return TransactionEntity.builder()
                .id(transaction.getId())
                .dueDate(transaction.getDueDatetime())
                .status(transaction.getStatus())
                .username(transaction.getUser().getUsername())
                .value(transaction.getValue())
                .walletRef(transaction.getUser().getWallet().getId())
                .build();
    }

    public Transaction toDomain(TransactionEntity transactionEntity){
        return Transaction.builder()
                .user(userRepository.findByUsername(transactionEntity.getUsername()))
                .Category(transactionEntity.getCategory())
                .dueDatetime(transactionEntity.getDueDate() == null ? LocalDate.from(LocalDateTime.now()).atStartOfDay() :transactionEntity.getDueDate() )
                .status(transactionEntity.getStatus())
                .Category(transactionEntity.getCategory())
                .build();
    }

}
