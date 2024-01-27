package com.hackathon.babyearn.model.utils;

import lombok.Data;

@Data
public class UserRequestDTO {
  private String username;
  private String lastname;
  private String firstname;
  private int pin;
}
