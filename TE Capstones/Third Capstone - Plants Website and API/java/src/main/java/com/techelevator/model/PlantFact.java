package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlantFact {

    private int id;
    @JsonProperty("common_name")
    private String commonName;
    @JsonProperty("scientific_name")
    private List<String> scientificName;
    @JsonProperty("other_name")
    private List<String> otherName;
    private String cycle;
    private String watering;
    private String[] sunlight;
    @JsonProperty("default_image")
    private ImageData defaultImage;
    private String description;

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

    public List<String> getScientificName() {
        return scientificName;
    }

    public void setScientificName(List<String> scientificName) {
        this.scientificName = scientificName;
    }

    public List<String> getOtherName() {
        return otherName;
    }

    public void setOtherName(List<String> otherName) {
        this.otherName = otherName;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public String[] getSunlight() {
        return sunlight;
    }

    public void setSunlight(String[] sunlight) {
        this.sunlight = sunlight;
    }

    public ImageData getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(ImageData defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    //ToString
    @Override
    public String toString() {
        return "PlantFact{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", scientificName=" + scientificName +
                ", otherName=" + otherName +
                ", cycle='" + cycle + '\'' +
                ", watering='" + watering + '\'' +
                ", sunlight='" + sunlight + '\'' +
                ", defaultImage=" + defaultImage +
                '}';
    }
}

