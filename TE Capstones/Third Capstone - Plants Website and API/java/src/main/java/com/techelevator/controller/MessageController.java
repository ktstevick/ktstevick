package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Message;
import com.techelevator.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class MessageController {

    private final MessageDao messageDao;

    public MessageController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/messages/user/{id}")
    public List<Message> getMessageByUserId(@PathVariable int id) {
        List<Message> message = messageDao.getMessageByUserId(id);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message Not Found");
        } else {
            return messageDao.getMessageByUserId(id);
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/messages", method = RequestMethod.POST)
    public Message create(@RequestBody @Valid Message message) {
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Message Not Created");
        } else {
            return messageDao.createMessage(message);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/messages/{id}", method = RequestMethod.GET)
    public Message getMessageById(@PathVariable int id) {
        Message message = messageDao.getMessageById(id);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message Not Found");
        } else {
            return messageDao.getMessageById(id);
        }
    }

    @RequestMapping(path = "/messages/{id}", method = RequestMethod.PUT)
    public Message update(@Valid @RequestBody Message message, @PathVariable int id){
        message.setMessageId(id);

        try{
            Message updatedMessage = messageDao.updateMessage(message);
            return updatedMessage;
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update");
        }
    }
}
