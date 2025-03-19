package com.stevick.teams_app.dao;

import com.stevick.teams_app.model.Team;
import java.util.List;

public interface TeamDao {
    Team getTeamById(int teamId);
    Team getTeamByName(String teamName);
    List<Team> getAllTeams();

//    Team createTeam(Team team);
//    Team updateTeam(Team team);
}
