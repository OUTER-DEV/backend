package com.hackathon.babyearn.repository;

import com.hackathon.babyearn.model.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface SuggestRepository extends JpaRepository<Suggest,Long> {
    Suggest findByUser_Id(Long Id);
}
