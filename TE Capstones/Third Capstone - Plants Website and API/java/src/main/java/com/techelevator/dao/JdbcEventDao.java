package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {
    private final JdbcTemplate jdbcTemplate;
    private String sql;
    private int lookUpId;
    private boolean isIdNeeded = false;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> getEventById(int eventId) {
        String sql = "SELECT" +
                        " ge.event_id," +
                        " ge.garden_id," +
                        " ge.user_id," +
                        " ge.event_name," +
                        " ge.event_description," +
                        " ge.event_coordinator," +
                        " ge.childcare_owner," +
                        " event_start_date," +
                        " event_end," +
                        " is_active," +
                        " ge.event_category," +
                        " v.volunteer_name" +
                            " FROM" +
                                " garden_event ge" +
                            " JOIN" +
                                " volunteer v" +
                            " ON" +
                                " ge.event_id = v.event_id" +
                            " WHERE" +
                                " ge.event_id = ?;";
        isIdNeeded = true;
        lookUpId = eventId;
        return getEventArray(sql, isIdNeeded);
    }

    @Override
    public List<Event> getEventByUserId(int userID) {
        String sql = "SELECT" +
                        " ge.event_id," +
                        " ge.garden_id," +
                        " ge.user_id," +
                        " ge.event_name," +
                        " ge.event_description," +
                        " ge.event_coordinator," +
                        " ge.childcare_owner," +
                        " event_start_date," +
                        " event_end," +
                        " is_active," +
                        " ge.event_category," +
                        " v.volunteer_name" +
                    " FROM" +
                        " garden_event ge" +
                    " JOIN" +
                        " volunteer v" +
                    " ON" +
                        " ge.event_id = v.event_id" +
                    " WHERE" +
                        " ge.user_id = ?;";
        isIdNeeded = true;
        return getEventArray(sql, isIdNeeded);
    }

    @Override
    public List<Event> getEvent() {
        String sql = "SELECT" +
                        " ge.event_id," +
                        " ge.garden_id," +
                        " ge.user_id," +
                        " ge.event_name," +
                        " ge.event_description," +
                        " ge.event_coordinator," +
                        " ge.childcare_owner," +
                        " event_start_date," +
                        " event_end," +
                        " is_active," +
                        " ge.event_category," +
                        " v.volunteer_name" +
                            " FROM" +
                                " garden_event ge" +
                            " LEFT JOIN" +
                                " volunteer v" +
                            " ON" +
                                " ge.event_id = v.event_id;";
        isIdNeeded = false;
        return getEventArray(sql, isIdNeeded);
    }

    @Override
    public List<Event> createEvent(Event event) {
        int eventId;
        String sqlEvent = "INSERT INTO" +
                            " garden_event (" +
                                " garden_id," +
                                " user_id," +
                                " event_name," +
                                " event_description," +
                                " event_coordinator," +
                                " childcare_owner," +
                                " event_start_date," +
                                " event_end," +
                                " is_active," +
                                " event_category)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                            " RETURNING event_id;";

        String sqlVolunteer = "INSERT INTO volunteer (event_id, volunteer_name) VALUES (?,?);";

        try {
            eventId = jdbcTemplate.queryForObject(sqlEvent, int.class, event.getGardenId(), event.getUserId(), event.getEventName(), event.getEventDescription(),
                    event.getEventCoordinator(), event.getChildcareOwner(), event.getEventStartDate(), event.getEventEnd(), event.getActive(), event.getEventCategory());


            for (int i = 0; i < event.getVolunteer().size(); i++) {
                jdbcTemplate.update(sqlVolunteer,eventId, event.getVolunteer().get(i));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        List<Event> newEvent = new ArrayList<>(getEventById(eventId));
        return newEvent;
    }

    @Override
    public List<Event> updateEvent(Event event) {
        int numberOfRows = 0;
        String sql = "UPDATE garden_event SET is_active = ?" +
                "WHERE event_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, event.getActive(), event.getEventId());

            if (numberOfRows == 0) {
                throw new DaoException("Match not found");
            }

        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot connect to database or server", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Cannot execute. Possible data integrity violation");
        }catch (DaoException e) {
            throw new DaoException("createEmployee() not implemented");
        }

        List<Event> updatedEvent = new ArrayList<>(getEventById(event.getEventId()));
        return updatedEvent;
    }

    private List<Event> getEventArray (String sql, Boolean isIdNeeded){
        List<Event> startingEventList = new ArrayList<>();
        List<Event> finalEventList = new ArrayList<>();
        List<String> runningVolunteerList = new ArrayList<>();
        SqlRowSet results;
        this.sql = sql;
        try {
            if(!isIdNeeded) {
                results = jdbcTemplate.queryForRowSet(sql);
            }
            else {
                results = jdbcTemplate.queryForRowSet(sql, lookUpId);
            }
            while (results.next()) {
                Event event = mapRowToEvent(results);
                startingEventList.add(event);
            }
            if (startingEventList.isEmpty()) {
                return startingEventList;
            }
            else if (startingEventList.size() == 1) {
                runningVolunteerList.add(startingEventList.get(0).getVolunteerName());
                startingEventList.get(0).setVolunteer(runningVolunteerList);
                return startingEventList;
            } else {
                int nextPlantId = startingEventList.get(0 + 1).getEventId();
                int plantListIndex = 0;
                for (int i = 0; i < startingEventList.size(); i++) {
                    if (i == 0){
                        finalEventList.add(startingEventList.get(0));
                        runningVolunteerList.add(startingEventList.get(0).getVolunteerName());
                        nextPlantId = startingEventList.get(i + 1).getEventId();
                    }
                    else if (i == startingEventList.size() - 1 && nextPlantId == startingEventList.get(i).getEventId()){
                        runningVolunteerList.add(startingEventList.get(i).getVolunteerName());
                        finalEventList.get(plantListIndex).setVolunteer(runningVolunteerList);
                        return finalEventList;
                    }
                    else if (nextPlantId == startingEventList.get(i).getEventId()){
                        runningVolunteerList.add(startingEventList.get(i).getVolunteerName());
                        nextPlantId = startingEventList.get(i + 1).getEventId();
                        if(nextPlantId != startingEventList.get(i).getEventId()){
                            finalEventList.get(plantListIndex).setVolunteer(new ArrayList<>(runningVolunteerList));
                            finalEventList.add(startingEventList.get(i + 1));
                            plantListIndex += 1;
                            runningVolunteerList.clear();
                        }
                    }
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return finalEventList;
    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setGardenId(rs.getInt("garden_id"));
        event.setUserId(rs.getInt("user_id"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDescription(rs.getString("event_description"));
        event.setEventCoordinator(rs.getString("event_coordinator"));
        event.setChildcareOwner(rs.getString("childcare_owner"));
        event.setEventStartDate(rs.getString("event_start_date"));
        event.setEventEnd(rs.getString("event_end"));
        event.setActive(rs.getBoolean("is_active"));
        event.setEventCategory(rs.getString("event_category"));
        event.setVolunteerName(rs.getString("volunteer_name"));
        return event;
    }
}
