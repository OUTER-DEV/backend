package com.hackathon.babyearn.repository.entity;

import com.hackathon.babyearn.model.SavingModel;
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
    private String name;
    private SavingEntity saving;
    private List<Transaction> transactions;
    private WalletEntity wallet;
}
