package com.techelevator.crm;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    // Variables
    String name;
    String species;

    // This can go in the Constructor later if necessary
    List<String> vaccinations = new ArrayList<>();

    // Getters and Setters
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return this.species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public List<String> getVaccinations() {
        return this.vaccinations;
    }
    public void setVaccinations(List<String> vaccinations) {
        this.vaccinations = vaccinations;
    }

    // Constructor
    public Pet(String name, String species) {
        this.name = name;
        this.species = species;
    }

    // Methods
    public String listVaccinations() {
        String vaxList = "";

        // I'm thinking a for-each loop?
        for (String currentVax : vaccinations) {
            vaxList = (vaxList + currentVax + ", ");
        }

        // Build subString
        String returnVaxList = vaxList.substring(0, (vaxList.length() - 2));

        return returnVaxList;
    }
}
