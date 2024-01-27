package com.hackathon.babyearn.service;


import com.hackathon.babyearn.repository.SuggestRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SuggestService {
    private final SuggestRepository suggestRepository;
}
