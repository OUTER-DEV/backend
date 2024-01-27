package com.hackathon.babyearn.service;

import com.hackathon.babyearn.model.Wallet;
import com.hackathon.babyearn.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public Wallet getWalletByUserId(Long userId){
        return walletRepository.findByUserId(userId);
    }
    public Wallet addNewBalance(Long id,Double value){
        Wallet toUpdate = walletRepository.getById(id);
        toUpdate.setBalance(toUpdate.getBalance()+value);
        return walletRepository.save(toUpdate);
    }

    public Wallet retrieveBalance(Long id,Double value){
        Wallet toUpdate = walletRepository.getById(id);
        double actualValue = toUpdate.getBalance()-value;

        if(actualValue>0){
            toUpdate.setBalance(actualValue);
            return walletRepository.save(toUpdate);
        }
        throw new IllegalArgumentException("Solde insuffisant,veillez faire un dépôt");
    }
}
