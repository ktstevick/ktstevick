package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
import com.techelevator.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
public class EventController {
    private final EventDao eventDao;

    public EventController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventDao.getEvent();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
    public List<Event> getEventById(@PathVariable int id) {
        List<Event> event = eventDao.getEventById(id);
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event Not Found");
        } else {
            return eventDao.getEventById(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/events/user/{id}", method = RequestMethod.GET)
    public List<Event> getPlantByUserId(@PathVariable int id) {
        List<Event> event = eventDao.getEventById(id);
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plant Not Found");
        } else {
            return eventDao.getEventByUserId(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public List<Event> create(@RequestBody @Valid Event event) {
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Event Not Created");
        } else {
            return eventDao.createEvent(event);
        }
    }

    @RequestMapping(path = "/events/{id}", method = RequestMethod.PUT)
    public List<Event> update(@Valid @RequestBody Event event, @PathVariable int id){
        event.setEventId(id);

        try{
            List<Event> updatedEvent = eventDao.updateEvent(event);
            return updatedEvent;
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update");
        }
    }
}
