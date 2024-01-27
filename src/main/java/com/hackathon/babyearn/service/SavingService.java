package com.hackathon.babyearn.service;


import com.hackathon.babyearn.model.SavingModel;
import com.hackathon.babyearn.repository.SavingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SavingService {
    private SavingRepository  savingRepository;

    public SavingModel getSavingById(Long id){
        return savingRepository.getById(id);
    }

    public SavingModel getSavingByUser_id(Long id){
        return savingRepository.findByOwner_Id(id);
    }
    public SavingModel addBalance(Long id,double value){
        SavingModel toUpdate = savingRepository.getById(id);
        toUpdate.setValue(toUpdate.getValue()+value);
        return savingRepository.save(toUpdate);
    }

    public SavingModel retrieveBalance(Long id, Double value){
        SavingModel toUpdate = savingRepository.getById(id);
        double actualValue = toUpdate.getValue()-value;

        if(actualValue>0){
            toUpdate.setValue(actualValue);
            return savingRepository.save(toUpdate);
        }
        throw new IllegalArgumentException("Veillez faire un dépot dans votre compte épargne pour en bénéficier");
    }

    public List<SavingModel> getAllByStatus(String status,Long userid){
        return savingRepository.findAllByStatusAndOwner_Id(status,userid);
    }
}
