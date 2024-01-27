package com.hackathon.babyearn.service;

import com.hackathon.babyearn.model.User;
import com.hackathon.babyearn.model.Wallet;
import com.hackathon.babyearn.model.utils.Credentials;
import com.hackathon.babyearn.model.utils.UserRequestDTO;
import com.hackathon.babyearn.repository.UserRepository;
import com.hackathon.babyearn.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final WalletRepository walletRepository;

  public User getUserProfile(Long userId) {
    return userRepository.findById(userId)
      .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
  }

  @Transactional
  public User addUserWithWallet(UserRequestDTO userRequestDTO) {

    if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
      throw new IllegalArgumentException("Le nom d'utilisateur est déjà utilisé.");
    }

    if (isValidPin(userRequestDTO.getPin())) {
      User savedUser = new User();
      BeanUtils.copyProperties(userRequestDTO, savedUser);
      savedUser = userRepository.save(savedUser);

      // Création d'un portefeuille associé à l'utilisateur
      Wallet wallet = new Wallet();
      wallet.setUser(savedUser);
      wallet.setBalance(0);
      walletRepository.save(wallet);

      // Mettre à jour l'utilisateur avec le portefeuille créé
      savedUser.setWallet(wallet);
      return savedUser;
    } else {
      throw new IllegalArgumentException("Le pin doit être un nombre à 4 chiffres.");
    }
  }

  @Transactional
  public User updateUser(Long userId, UserRequestDTO userRequest) {
    User existingUser = userRepository.findById(userId)
      .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

    if (userRequest.getUsername() != null) {
      existingUser.setUsername(userRequest.getUsername());
    }
    if (userRequest.getLastname() != null) {
      existingUser.setLastname(userRequest.getLastname());
    }
    if (userRequest.getFirstname() != null) {
      existingUser.setFirstname(userRequest.getFirstname());
    }
    if (userRequest.getPin() > 0) {
      existingUser.setPin(userRequest.getPin());
    }

    User updatedUser = userRepository.save(existingUser);

    return updatedUser;
  }

  private boolean isValidPin(int pin) {
    String pinString = String.valueOf(pin);
    return pinString.length() == 4 && pinString.matches("\\d{4}");
  }

  @Transactional
  public User authenticateUser(Credentials credentials) {
    User user = userRepository.findByUsername(credentials.getUsername());

    if (user != null && isValidPin(credentials.getPin()) && credentials.getPin() == user.getPin()) {
      return user;
    } else {
      throw new IllegalArgumentException("L'authentification a échoué. Vérifiez vos informations d'identification.");
    }
  }

}
