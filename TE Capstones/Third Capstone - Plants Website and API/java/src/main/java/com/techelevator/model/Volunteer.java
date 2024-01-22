package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Volunteer {
    @JsonProperty("volunteer_id")
    private int volunteerId;
    @JsonProperty("event_id")
    private int eventId;
    @JsonProperty("volunteer_name")
    private String volunteerName;

    //Setters and Getters
    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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
        return "Volunteer{" +
                "volunteerId=" + volunteerId +
                ", eventId=" + eventId +
                ", volunteerName='" + volunteerName + '\'' +
                '}';
    }
}
