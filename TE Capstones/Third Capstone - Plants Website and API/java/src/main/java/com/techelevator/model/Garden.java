package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Garden {
    @JsonProperty("garden_id")
    private int gardenId;
    @JsonProperty("garden_name")
    private String gardenName;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("street_address")
    private String streetAddress;
    @JsonProperty("garden_city")
    private String gardenCity;
    @JsonProperty("garden_state")
    private String gardenState;
    @JsonProperty("garden_zip")
    private int gardenZip;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("is_public")
    private Boolean isPublicGarden;
    @JsonProperty("garden_type")
    private String gardenType;

    //Setters and Getters

    public int getGardenId() {
        return gardenId;
    }

    public void setGardenId(int gardenId) {
        this.gardenId = gardenId;
    }

    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getGardenCity() {
        return gardenCity;
    }

    public void setGardenCity(String gardenCity) {
        this.gardenCity = gardenCity;
    }

    public String getGardenState() {
        return gardenState;
    }

    public void setGardenState(String gardenState) {
        this.gardenState = gardenState;
    }

    public int getGardenZip() {
        return gardenZip;
    }

    public void setGardenZip(int gardenZip) {
        this.gardenZip = gardenZip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getPublicGarden() {
        return isPublicGarden;
    }

    public void setPublicGarden(Boolean publicGarden) {
        isPublicGarden = publicGarden;
    }

    public String getGardenType() {
        return gardenType;
    }

    public void setGardenType(String gardenType) {
        this.gardenType = gardenType;
    }

    @Override
    public String toString() {
        return "Garden{" +
                "gardenId=" + gardenId +
                ", gardenName='" + gardenName + '\'' +
                ", userId=" + userId +
                ", streetAddress='" + streetAddress + '\'' +
                ", gardenCity='" + gardenCity + '\'' +
                ", gardenState='" + gardenState + '\'' +
                ", gardenZip=" + gardenZip +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isPublicGarden=" + isPublicGarden +
                ", gardenType='" + gardenType + '\'' +
                '}';
    }
}
