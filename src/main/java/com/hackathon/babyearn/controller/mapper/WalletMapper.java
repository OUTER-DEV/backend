package com.hackathon.babyearn.controller.mapper;

import com.hackathon.babyearn.model.Wallet;
import com.hackathon.babyearn.repository.entity.WalletEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WalletMapper {


    public Wallet toDomain(WalletEntity walletEntity){
        return Wallet.builder()
                .id(walletEntity.getId())
                .name(walletEntity.getName())
                .balance(walletEntity.getBalance())
                .user(walletEntity.getOwner())
                .build();
    }

    public WalletEntity toRest(Wallet wallet){
            return WalletEntity.builder()
                    .balance(wallet.getBalance())
                    .name(wallet.getName())
                    .build();
    }
}
