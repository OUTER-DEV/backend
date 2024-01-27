package com.hackathon.babyearn.repository;

import com.hackathon.babyearn.model.User;
import com.hackathon.babyearn.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  User findByUsername(String username);
  boolean existsByUsername(String username);
}
