package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plant {
    @JsonProperty("plant_id")
    private int id;
    @JsonProperty("plant_img")
    private String plantImg;
    @JsonProperty("common_name")
    private String commonName;
    @JsonProperty("scientific_name")
    private String scientificName;
    @JsonProperty("other_name")
    private String otherName;
    private String watering;
    private List<String> sunlight;
    @JsonProperty("regular_img_url")
    private String imgUrl;
    @JsonProperty("plant_description")
    private String description;
    @JsonProperty("api_plant_id")
    private int apiPlantId;
    @JsonProperty("sunshine_description")
    private String sunshineDescription;
    //garden_id is located on bridge table, not in the plant table
    @JsonProperty("garden_id")
    private int gardenId;
    @JsonProperty("is_active")
    private boolean isActive;

    //Setters and Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlantImg() {
        return plantImg;
    }

    public void setPlantImg(String plantImg) {
        this.plantImg = plantImg;
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

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public List<String> getSunlight() {
        return sunlight;
    }

    public void setSunlight(List<String> sunlight) {
        this.sunlight = sunlight;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getApiPlantId() {
        return apiPlantId;
    }

    public void setApiPlantId(int apiPlantId) {
        this.apiPlantId = apiPlantId;
    }

    public String getSunshineDescription() {
        return sunshineDescription;
    }

    public void setSunshineDescription(String sunshineDescription) {
        this.sunshineDescription = sunshineDescription;
    }

    public int getGardenId() {
        return gardenId;
    }

    public void setGardenId(int gardenId) {
        this.gardenId = gardenId;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", plantImg='" + plantImg + '\'' +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", otherName='" + otherName + '\'' +
                ", watering='" + watering + '\'' +
                ", sunlight=" + sunlight +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", apiPlantId=" + apiPlantId +
                ", sunshineDescription='" + sunshineDescription + '\'' +
                ", gardenId=" + gardenId +
                ", isActive=" + isActive +
                '}';
    }
}
