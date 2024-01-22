package com.techelevator.services;

import com.techelevator.model.DiseaseFact;

import java.util.List;

public interface DiseaseFactService {
    List<DiseaseFact> getDiseaseFactByName(String name);
}
