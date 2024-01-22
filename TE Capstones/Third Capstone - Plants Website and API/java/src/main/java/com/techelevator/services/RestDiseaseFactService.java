package com.techelevator.services;

import com.techelevator.model.DiseaseFact;
import com.techelevator.model.DiseaseFactsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
@Component
public class RestDiseaseFactService implements DiseaseFactService{

    private RestTemplate restTemplate = new RestTemplate();
    private DiseaseFactsResponse response;
    public List<DiseaseFact> getDiseaseFactByName(String name) {

        String apiKey = "sk-H0RV656a8958725bb3267"; // API key
        String baseUrl = "https://perenual.com/api/pest-disease-list?key="; // URL of the API

        response = restTemplate.getForObject(baseUrl + apiKey + "&q=" + name, DiseaseFactsResponse.class);

        if (response != null && response.getData() != null) {
            return response.getData();
        } else {
            return Collections.emptyList();
        }
    }
}
