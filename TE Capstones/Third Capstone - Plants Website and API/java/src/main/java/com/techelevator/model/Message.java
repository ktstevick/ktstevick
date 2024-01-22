package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    @JsonProperty("message_id")
    private int messageId;
    @JsonProperty("to_user_id")
    private int toUserId;
    @JsonProperty("from_user_id")
    private int fromUserId;
    @JsonProperty("message_body")
    private String messageBody;
    @JsonProperty("date_and_time")
    private String dateAndTime;
    @JsonProperty("is_read")
    private Boolean isRead;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", toUserId=" + toUserId +
                ", fromUserId=" + fromUserId +
                ", messageBody='" + messageBody + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
