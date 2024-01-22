package com.techelevator.model;

import java.util.List;

public class DiseaseFactsResponse {

    private List<DiseaseFact> data;

    // Getters and setters
    public List<DiseaseFact> getData() {
        return data;
    }

    public void setData(List<DiseaseFact> data) {
        this.data = data;
    }
}
