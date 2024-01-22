package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Garden;
import com.techelevator.model.Plant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGardenDao implements GardenDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGardenDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Garden getGardenById(int gardenId) {
        Garden garden = null;
        String sql = "SELECT garden_id, garden_name, user_id, street_address, garden_city, garden_state, garden_zip," +
                " phone_number, is_public, garden_type FROM garden WHERE garden_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gardenId);
            if (results.next()) {
                garden = mapRowToGarden(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return garden;
    }

    @Override
    public List<Garden> getGardenByUserId(int userId) {
        List<Garden> gardens = new ArrayList<>();
        String sql = "SELECT * FROM garden g" +
                " JOIN users u ON u.user_id = g.user_id" +
                " WHERE u.user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Garden garden = mapRowToGarden(results);
                gardens.add(garden);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return gardens;
    }
    @Override
    public Garden createGarden(Garden garden) {
        Garden newGarden = null;
        String sql = "INSERT INTO garden (garden_name, user_id, street_address, garden_city, garden_state, garden_zip," +
                " phone_number, is_public, garden_type)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING garden_id;";
        try {
            int gardenId = jdbcTemplate.queryForObject(sql, int.class, garden.getGardenName(), garden.getUserId(), garden.getStreetAddress(),
                    garden.getGardenCity(), garden.getGardenState(), garden.getGardenZip(), garden.getPhoneNumber(), garden.getPublicGarden(),
                    garden.getGardenType());
            newGarden = getGardenById(gardenId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return newGarden;
    }

    @Override
    public List<Garden> getGarden() {
        List<Garden> gardens = new ArrayList<>();
        String sql = "SELECT garden_id, garden_name, user_id, street_address, garden_city, garden_state, garden_zip," +
                " phone_number, is_public, garden_type FROM garden;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Garden garden = mapRowToGarden(results);
                gardens.add(garden);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return gardens;
    }

    @Override
    public Garden updateGarden(Garden garden) {
        int numberOfRows = 0;
        Garden updatedGarden;
        String sql = "UPDATE garden SET is_public = ?" +
                "WHERE garden_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, garden.getPublicGarden(), garden.getGardenId());

            if (numberOfRows == 0) {
                throw new DaoException("Match not found");
            } else {
                updatedGarden = getGardenById(garden.getGardenId());
            }

        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot connect to database or server", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Cannot execute. Possible data integrity violation");
        }catch (DaoException e) {
            throw new DaoException("createEmployee() not implemented");
        }

        return updatedGarden;
    }


    private Garden mapRowToGarden(SqlRowSet rs) {
        Garden garden = new Garden();
        garden.setGardenId(rs.getInt("garden_id"));
        garden.setGardenName(rs.getString("garden_name"));
        garden.setUserId(rs.getInt("user_id"));
        garden.setStreetAddress(rs.getString("street_address"));
        garden.setGardenCity(rs.getString("garden_city"));
        garden.setGardenState(rs.getString("garden_state"));
        garden.setGardenZip(rs.getInt("garden_zip"));
        garden.setPhoneNumber(rs.getString("phone_number"));
        garden.setPublicGarden(rs.getBoolean("is_public"));
        garden.setGardenType(rs.getString("garden_type"));
        return garden;
    }
}
