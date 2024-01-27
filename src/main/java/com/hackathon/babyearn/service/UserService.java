package com.hackathon.babyearn.service;

import com.hackathon.babyearn.model.User;
import com.hackathon.babyearn.model.Wallet;
import com.hackathon.babyearn.model.utils.Credentials;
import com.hackathon.babyearn.repository.UserRepository;
import com.hackathon.babyearn.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public User addUserWithWallet(User user) {
        if (isValidPin(user.getPin())) {
            // Sauvegarde de l'utilisateur
            User savedUser = userRepository.save(user);

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
