package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public interface EventDao {
    List<Event> getEvent();
    List<Event> getEventById(int eventid);
    List<Event> createEvent(Event event);
    List<Event> getEventByUserId(int userid);
    List<Event> updateEvent(Event event);
}
