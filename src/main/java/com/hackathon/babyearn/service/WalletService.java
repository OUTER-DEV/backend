package com.hackathon.babyearn.service;

import com.hackathon.babyearn.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WalletService {
    private final WalletRepository walletRepository;
}
