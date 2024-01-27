package com.hackathon.babyearn.repository.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestEntity {
    private Long id;
    private Double value;
    private LocalDate startDate;
    private LocalDate endingDate;
    private String description;
    private String status;
}
