package com.techelevator.dao;

import com.techelevator.model.Reminder;

import java.util.List;

public interface ReminderDao {
    List<Reminder> getReminderByUserId(int id);
    Reminder createReminder(Reminder reminder);
    Reminder getReminderById(int id);
    Reminder updateReminder(Reminder reminder);
}
