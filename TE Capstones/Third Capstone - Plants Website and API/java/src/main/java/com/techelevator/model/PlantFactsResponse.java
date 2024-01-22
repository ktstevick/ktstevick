package com.techelevator.model;

import java.util.List;

public class PlantFactsResponse {
    private List<PlantFact> data;

    // Getters and setters
    public List<PlantFact> getData() {
        return data;
    }

    public void setData(List<PlantFact> data) {
        this.data = data;
    }
}
