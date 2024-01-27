package com.hackathon.babyearn.controller;

import com.hackathon.babyearn.model.Wallet;
import com.hackathon.babyearn.service.WalletService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WalletController {

  private final WalletService walletService;

  @GetMapping("/wallet/{userId}")
  public ResponseEntity<Wallet> getWalletByUserId(@PathVariable Long userId, HttpSession session) {
    // Vérifier si l'utilisateur est authentifié
    Long authenticatedUserId = (Long) session.getAttribute("userId");

    if (authenticatedUserId != null && authenticatedUserId.equals(userId)) {
      try {
        // Utiliser userId pour obtenir le portefeuille de l'utilisateur
        Wallet userWallet = walletService.getWalletByUserId(userId);
        return new ResponseEntity<>(userWallet, HttpStatus.OK);
      } catch (EntityNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping("/wallet/{id}/deposit")
  public ResponseEntity<Wallet> addNewBalance(@PathVariable Long id,
                                              @RequestParam Double value,
                                              HttpSession session) {\
    Long authenticatedUserId = (Long) session.getAttribute("userId");

    if (authenticatedUserId != null) {
      try {
        // Ajouter un nouveau solde au portefeuille de l'utilisateur
        Wallet updatedWallet = walletService.addNewBalance(id, value);
        return new ResponseEntity<>(updatedWallet, HttpStatus.OK);
      } catch (EntityNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping("/wallet/{id}/withdraw")
  public ResponseEntity<Wallet> retrieveBalance(@PathVariable Long id,
                                                @RequestParam Double value,
                                                HttpSession session) {
    Long authenticatedUserId = (Long) session.getAttribute("userId");

    if (authenticatedUserId != null) {
      try {
        // Retirer le solde du portefeuille de l'utilisateur
        Wallet updatedWallet = walletService.retrieveBalance(id, value);
        return new ResponseEntity<>(updatedWallet, HttpStatus.OK);
      } catch (EntityNotFoundException | IllegalArgumentException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
}
