package com.stevick.teams_app.controller;

import com.stevick.teams_app.dao.JdbcTeamDao;
import com.stevick.teams_app.model.Team;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final JdbcTeamDao teamDao;

    public TeamController(JdbcTeamDao teamDao) { this.teamDao = teamDao; }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Team get(@PathVariable int id) {
        Team team = teamDao.getTeamById(id);
        if(team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found.");
        } else {
            return team;
        }
    }
}
