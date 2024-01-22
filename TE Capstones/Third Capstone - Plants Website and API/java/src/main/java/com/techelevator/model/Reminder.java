package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reminder {
    @JsonProperty("reminder_id")
    private int reminderId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("reminder_date_and_time")
    private String reminderDateTime;
    @JsonProperty("reminder_details")
    private String reminderDetails;
    @JsonProperty("reminder_icon")
    private String reminderIcon;

    //Getters and Setters
    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(String reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public String getReminderDetails() {
        return reminderDetails;
    }

    public void setReminderDetails(String reminderDetails) {
        this.reminderDetails = reminderDetails;
    }

    public String getReminderIcon() {
        return reminderIcon;
    }

    public void setReminderIcon(String reminderIcon) {
        this.reminderIcon = reminderIcon;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @JsonProperty("is_active")
    private Boolean isActive;

    //ToString

    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId=" + reminderId +
                ", userId=" + userId +
                ", reminderDateTime='" + reminderDateTime + '\'' +
                ", reminderDetails='" + reminderDetails + '\'' +
                ", reminderIcon='" + reminderIcon + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
