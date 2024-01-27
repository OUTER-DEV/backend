package com.hackathon.babyearn.controller;


import com.hackathon.babyearn.model.Suggest;
import com.hackathon.babyearn.service.SuggestService;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SuggestController {
    private final SuggestService suggestService;

    @GetMapping("/goals/{id}")
    public Suggest getSuggestModel(@PathVariable  Long id){
        return suggestService.suggestFinancialEducation(id);
    }
}
