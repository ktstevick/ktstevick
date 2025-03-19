package com.stevick.teams_app.controller;

import com.stevick.teams_app.dao.JdbcTeamDao;
import com.stevick.teams_app.model.Team;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final JdbcTeamDao teamDao;

    public TeamController(JdbcTeamDao teamDao) { this.teamDao = teamDao; }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        teams = teamDao.getAllTeams();

        if(teams == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teams not found.");
        } else {
            return teams;
        }
    }

    @RequestMapping(path = "/{search}", method = RequestMethod.GET)
    public Team get(@PathVariable String search) {
        Team team= null;

        if(isNumeric(search)) {
            team = teamDao.getTeamById(Integer.parseInt(search));
        } else {
            team = teamDao.getTeamByName(search);
        }

        if(team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found.");
        } else {
            return team;
        }
    }

    // UTILITY
    public boolean isNumeric(String str) {
        try { Integer.parseInt(str); return true; }
        catch (Exception e){ return false; }
    }
}
