package com.hackathon.babyearn.repository;

import com.hackathon.babyearn.model.SavingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface SavingRepository extends JpaRepository<SavingModel,Long> {

    SavingModel findByOwner_Id(Long ownerId);
    List<SavingModel> findAllByStatusAndOwner_Id(String status,Long ownerId);
}
