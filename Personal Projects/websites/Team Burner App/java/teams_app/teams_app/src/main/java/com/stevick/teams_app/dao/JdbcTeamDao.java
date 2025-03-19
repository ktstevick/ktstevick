package com.stevick.teams_app.dao;

import com.stevick.teams_app.exception.DaoException;
import com.stevick.teams_app.model.Team;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTeamDao implements TeamDao{
    private final JdbcTemplate jdbcTemplate;
    private String sql;

    public JdbcTeamDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Team getTeamById(int teamId) {
        Team team = null;
        String sql = "SELECT * FROM team WHERE team_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
            while (results.next()) {
                team = mapRowToTeam(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect somehow!", e);
        }

        return team;
    }

    @Override
    public Team getTeamByName(String teamName) {
        Team team = null;
        String sql = "SELECT * FROM team WHERE name = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamName);
            while (results.next()) {
                team = mapRowToTeam(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect somehow!", e);
        }

        return team;
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM team;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Team team = mapRowToTeam(results);
                teams.add(team);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect somehow!", e);
        }

        return teams;
    }

//    @Override
//    public Team createTeam(Team team) { }
//    @Override
//    public Team updateTeam(Team team) { }

    private Team mapRowToTeam(SqlRowSet rs) {
        Team team = new Team();
        team.setId(rs.getInt("team_id"));
        team.setName(rs.getString("name"));
        team.setRoster(rs.getString("roster"));

        return team;
    }
}
