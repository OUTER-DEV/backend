package com.hackathon.babyearn.repository;


import com.hackathon.babyearn.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
