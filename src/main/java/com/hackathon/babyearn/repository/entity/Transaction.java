package com.hackathon.babyearn.repository.entity;


import com.hackathon.babyearn.model.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Long id;
    private String username;
    private String walletRef;
    private Double value;
    private LocalDateTime dueDate;
    private Status status;
}
