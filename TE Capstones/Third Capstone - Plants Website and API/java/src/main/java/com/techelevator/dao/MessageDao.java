package com.techelevator.dao;

import com.techelevator.model.Message;

import java.util.List;

public interface MessageDao {
    List<Message> getMessageByUserId(int id);
    Message createMessage(Message message);
    Message getMessageById(int id);
    Message updateMessage(Message message);
}
