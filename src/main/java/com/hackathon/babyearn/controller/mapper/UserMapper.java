package com.hackathon.babyearn.controller.mapper;


import com.hackathon.babyearn.model.User;
import com.hackathon.babyearn.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final SavingMapper savingMapper;
    public User toDomain(UserEntity userEntity){
        return User.builder()
                .firstname(userEntity.getFirstname())
                .username(userEntity.getUsername())
                .lastname(userEntity.getLastname())
                .pin(userEntity.getPin())
                .build();
    }

    public UserEntity toRest(User user){
        return UserEntity.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .pin(user.getPin())
                .saving(user.getSavingModel().stream().map(savingMapper::toRest).collect(Collectors.toList()))
                .build();
    }
}

