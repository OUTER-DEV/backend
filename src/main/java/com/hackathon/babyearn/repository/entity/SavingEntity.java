package com.hackathon.babyearn.repository.entity;

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
public class SavingEntity {
    private Long id;
    private Double value;
    private LocalDateTime localDateTime;
    private String goals;
    private String category;
    private String status;
}
