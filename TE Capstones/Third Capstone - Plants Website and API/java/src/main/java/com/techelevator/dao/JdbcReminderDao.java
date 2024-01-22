package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Message;
import com.techelevator.model.Reminder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReminderDao implements ReminderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcReminderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Reminder> getReminderByUserId(int userId) {
        List<Reminder> reminders = new ArrayList<>();
        String sql = "SELECT * FROM reminder WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Reminder reminder = mapRowToReminder(results);
                reminders.add(reminder);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return reminders;
    }

    @Override
    public Reminder getReminderById(int reminderId) {
        Reminder reminder = null;
        String sql = "SELECT * FROM reminder WHERE reminder_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reminderId);
            if (results.next()) {
                reminder = mapRowToReminder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return reminder;
    }

    @Override
    public Reminder createReminder(Reminder reminder) {
        Reminder newReminder = null;
        String sql = "INSERT INTO " +
                        " reminder (" +
                            " user_id," +
                            " reminder_date_and_time," +
                            " reminder_details," +
                            " reminder_icon," +
                            " is_active)" +
                        " VALUES (?, ?, ?, ?, ?)" +
                        " RETURNING reminder_id;";
        try {
            int reminderId = jdbcTemplate.queryForObject(sql, int.class, reminder.getUserId(), reminder.getReminderDateTime(),
                reminder.getReminderDetails(), reminder.getReminderIcon(), reminder.getActive());
            newReminder = getReminderById(reminderId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return newReminder;
    }

    @Override
    public Reminder updateReminder(Reminder reminder) {
        int numberOfRows = 0;
        Reminder updatedReminder;
        String sql = "UPDATE reminder SET is_active = ?" +
                "WHERE reminder_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, reminder.getActive(), reminder.getReminderId());

            if (numberOfRows == 0) {
                throw new DaoException("Match not found");
            } else {
                updatedReminder = getReminderById(reminder.getReminderId());
            }

        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot connect to database or server", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Cannot execute. Possible data integrity violation");
        }catch (DaoException e) {
            throw new DaoException("createEmployee() not implemented");
        }

        return updatedReminder;
    }

    private Reminder mapRowToReminder(SqlRowSet rs) {
        Reminder reminder = new Reminder();
        reminder.setUserId (rs.getInt("user_id"));
        reminder.setReminderDateTime (rs.getString("reminder_date_and_time"));
        reminder.setReminderDetails (rs.getString("reminder_details"));
        reminder.setReminderIcon (rs.getString("reminder_icon"));
        reminder.setActive (rs.getBoolean("is_active"));
        return reminder;
    }
}
