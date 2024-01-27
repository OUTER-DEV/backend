package com.hackathon.babyearn.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private int pin;
    private List<SavingEntity> saving;
    private List<TransactionEntity> transactionEntities;
    private WalletEntity wallet;
}
