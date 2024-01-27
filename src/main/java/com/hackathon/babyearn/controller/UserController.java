package com.hackathon.babyearn.controller;

import com.hackathon.babyearn.model.User;
import com.hackathon.babyearn.model.utils.Credentials;
import com.hackathon.babyearn.model.utils.UserRequestDTO;
import com.hackathon.babyearn.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

  private final UserService userService;


  @PostMapping("/signup")
  public ResponseEntity<User> signUp(@RequestBody UserRequestDTO userRequestDTO) {
    try {
      validateUser(userRequestDTO);

      User createdUser = userService.addUserWithWallet(userRequestDTO);
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  @PostMapping("/signin")
  public ResponseEntity<User> signIn(@RequestBody Credentials credentials, HttpSession session) {
    try {
      User authenticatedUser = userService.authenticateUser(credentials);

      session.setAttribute("userId", authenticatedUser.getId());

      return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping("/user/{userId}/profile")
  public ResponseEntity<User> getUserProfile(@PathVariable Long userId, HttpSession session) {
    Long authenticatedUserId = (Long) session.getAttribute("userId");

    if (authenticatedUserId != null && authenticatedUserId.equals(userId)) {
      try {
        User userProfile = userService.getUserProfile(userId);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
      } catch (EntityNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @PutMapping("/user/{userId}")
  public ResponseEntity<User> updateUser(@PathVariable Long userId,
                                         @RequestBody UserRequestDTO userRequest,
                                         HttpSession session) {
    Long authenticatedUserId = (Long) session.getAttribute("userId");

    if (authenticatedUserId != null && authenticatedUserId.equals(userId)) {
      try {
        User updatedUser = userService.updateUser(userId, userRequest);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
      } catch (EntityNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
  @GetMapping("/logout")
  public ResponseEntity<String> logout(HttpSession session) {

    session.removeAttribute("userId");
    return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
  }

  private void validateUser(UserRequestDTO user) {
    if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
      user.getLastname() == null || user.getLastname().trim().isEmpty() ||
      user.getFirstname() == null || user.getFirstname().trim().isEmpty()) {
      throw new IllegalArgumentException("Les champs  ne peuvent pas être vides.");
    }
  }

}
