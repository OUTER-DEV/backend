package com.hackathon.babyearn.controller;

import com.hackathon.babyearn.model.User;
import com.hackathon.babyearn.model.utils.Credentials;
import com.hackathon.babyearn.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<User> signUp(@RequestBody User user) {
    try {
      validateUser(user);

      User createdUser = userService.addUserWithWallet(user);
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      // Handle invalid input
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<User> signIn(@RequestBody Credentials credentials) {
    try {
      User authenticatedUser = userService.authenticateUser(credentials);
      return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  private void validateUser(User user) {
    if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
      user.getLastname() == null || user.getLastname().trim().isEmpty() ||
      user.getFirstname() == null || user.getFirstname().trim().isEmpty()) {
      throw new IllegalArgumentException("Les champs  ne peuvent pas être vides.");
    }
  }

}
