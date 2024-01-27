package com.hackathon.babyearn.model.utils;

import lombok.Data;

@Data
public class TransactionDTO {
    private String name;
    private String category;
    private double value;
    private String status;
}
