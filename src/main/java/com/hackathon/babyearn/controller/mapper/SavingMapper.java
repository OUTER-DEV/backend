package com.hackathon.babyearn.controller.mapper;


import com.hackathon.babyearn.model.SavingModel;
import com.hackathon.babyearn.model.User;
import com.hackathon.babyearn.repository.UserRepository;
import com.hackathon.babyearn.repository.entity.SavingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
public class SavingMapper {

    private static UserRepository userRepository ;
    public static SavingModel toDomain(SavingEntity saving,Long userId){
        User user = userRepository.getById(userId);
        return SavingModel.builder()
                        .date(saving.getLocalDateTime()  == null ? LocalDate.from(LocalDateTime.now()) : saving.getLocalDateTime().toLocalDate())
                .value(saving.getValue())
                .category(saving.getCategory())
                .owner(user)
                .goal(saving.getGoals())
                .status("TO DO")
                .build();
    }

    public SavingEntity toRest(SavingModel savingModel){
        return SavingEntity.builder()
                .id(savingModel.getId())
                .category(savingModel.getCategory())
                .goals(savingModel.getGoal())
                .localDateTime(savingModel.getDate().atStartOfDay())
                .value(savingModel.getValue())
                .status(savingModel.getStatus())
                .build();
    }


}
