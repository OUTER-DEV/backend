package com.hackathon.babyearn.repository;

import com.hackathon.babyearn.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByDueDatetime(Date Date);
    List<Transaction> findAllByUser_Id(Long id);

    Transaction findByUser_Username(String username);
}
