package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Event {
    @JsonProperty("event_id")
    private int eventId;
    @JsonProperty("garden_id")
    private int gardenId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("event_description")
    private String eventDescription;
    @JsonProperty("event_coordinator")
    private String eventCoordinator;
    @JsonProperty("childcare_owner")
    private String childcareOwner;
    @JsonProperty("event_start_date")
    private String eventStartDate;
    @JsonProperty("event_end")
    private String eventEnd;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("event_category")
    private String eventCategory;
    private List<String> volunteer;
    @JsonProperty("volunteer_name")
    private String volunteerName;

    //Setters and Getters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getGardenId() {
        return gardenId;
    }

    public void setGardenId(int gardenId) {
        this.gardenId = gardenId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventCoordinator() {
        return eventCoordinator;
    }

    public void setEventCoordinator(String eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
    }

    public String getChildcareOwner() {
        return childcareOwner;
    }

    public void setChildcareOwner(String childcareOwner) {
        this.childcareOwner = childcareOwner;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public List<String> getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(List<String> volunteer) {
        this.volunteer = volunteer;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    //ToString

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", gardenId=" + gardenId +
                ", userId=" + userId +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventCoordinator='" + eventCoordinator + '\'' +
                ", childcareOwner='" + childcareOwner + '\'' +
                ", eventStartDate='" + eventStartDate + '\'' +
                ", eventEnd='" + eventEnd + '\'' +
                ", isActive=" + isActive +
                ", eventCategory='" + eventCategory + '\'' +
                ", volunteer=" + volunteer +
                ", volunteerName='" + volunteerName + '\'' +
                '}';
    }
}
