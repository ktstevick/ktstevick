package com.techelevator.services;

import com.techelevator.model.PlantFact;

import java.util.List;

public interface PlantFactService {

    List<PlantFact> getPlantFactByName(String name);
}
