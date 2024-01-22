package com.techelevator.services;


import com.techelevator.model.PlantDescriptionResponse;
import com.techelevator.model.PlantFact;
import com.techelevator.model.PlantFactsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Component
public class RestPlantFactService implements PlantFactService {

    private RestTemplate restTemplate = new RestTemplate();
    private PlantFactsResponse response;
    public List<PlantFact> getPlantFactByName(String name) {
        String apiKey = "sk-H0RV656a8958725bb3267"; // API key
        String baseUrl = "https://perenual.com/api/species-list"; // URL of the API

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("key", apiKey)
                .queryParam("q", name)
                .build();

        response = restTemplate.getForObject(uriComponents.toUriString(), PlantFactsResponse.class);

        if (response != null && response.getData() != null) {
            PlantFactsResponse results = getPlantDescription(response);
            return results.getData();
        } else {
            return Collections.emptyList();
        }
    }

    public PlantFactsResponse getPlantDescription(PlantFactsResponse response) {
        String apiKey = "sk-H0RV656a8958725bb3267"; // API key
        //TODO: make url key static
        String baseUrl = "https://perenual.com/api/species/details"; // URL of the API

        for (int i = 0; i < response.getData().size(); i++) {
            int id = response.getData().get(i).getId();
            PlantDescriptionResponse descriptionResponse = restTemplate.getForObject(baseUrl + "/" + id + "?key=" + apiKey, PlantDescriptionResponse.class);
            response.getData().get(i).setDescription(descriptionResponse.getDescription());
        }
        return response;
    }
}
