package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DiseaseFact {
    private int id;
    @JsonProperty("common_name")
    private String commonName;
    @JsonProperty("scientific_name")
    private String scientificName;
    @JsonProperty("other_name")
    private List<String> otherName;
    private String family;
    @JsonProperty("description")
    private List<DiseaseDescriptionResponse> diseaseDescription;
    @JsonProperty("solution")
    private List<DiseaseSolutionDescriptionResponse> solutionDescription;
    private List<String> host;
    private List<DiseaseImagesResponse> images;

    //Setters and Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public List<String> getOtherName() {
        return otherName;
    }

    public void setOtherName(List<String> otherName) {
        this.otherName = otherName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<DiseaseDescriptionResponse> getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(List<DiseaseDescriptionResponse> diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public List<DiseaseSolutionDescriptionResponse> getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(List<DiseaseSolutionDescriptionResponse> solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }

    public List<DiseaseImagesResponse> getImages() {
        return images;
    }

    public void setImages(List<DiseaseImagesResponse> images) {
        this.images = images;
    }
}
