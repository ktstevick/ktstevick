package com.techelevator.controller;

import com.techelevator.dao.ReminderDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Message;
import com.techelevator.model.Reminder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ReminderController {
    private final ReminderDao reminderDao;

    public ReminderController(ReminderDao reminderDao) {
        this.reminderDao = reminderDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/reminders/user/{id}")
    public List<Reminder> getMessageByUserId(@PathVariable int id) {
        List<Reminder> reminder = reminderDao.getReminderByUserId(id);
        if (reminder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reminder Not Found");
        } else {
            return reminderDao.getReminderByUserId(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/reminders/{id}", method = RequestMethod.GET)
    public Reminder getMessageById(@PathVariable int id) {
        Reminder reminder = reminderDao.getReminderById(id);
        if (reminder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message Not Found");
        } else {
            return reminderDao.getReminderById(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/reminders", method = RequestMethod.POST)
    public Reminder create(@RequestBody @Valid Reminder reminder) {
        if (reminder == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Reminder Not Created");
        } else {
            return reminderDao.createReminder(reminder);
        }
    }

    @RequestMapping(path = "/reminders/{id}", method = RequestMethod.PUT)
    public Reminder update(@Valid @RequestBody Reminder reminder, @PathVariable int id){
        reminder.setReminderId(id);

        try{
            Reminder updatedReminder = reminderDao.updateReminder(reminder);
            return updatedReminder;
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update");
        }
    }
}
