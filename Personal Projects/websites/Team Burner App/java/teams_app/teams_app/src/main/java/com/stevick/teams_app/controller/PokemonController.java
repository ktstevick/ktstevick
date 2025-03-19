package com.stevick.teams_app.controller;

import com.stevick.teams_app.dao.JdbcPokemonDao;
import com.stevick.teams_app.dao.JdbcTeamDao;
import com.stevick.teams_app.dao.PokemonDao;
import com.stevick.teams_app.model.Pokemon;
import com.stevick.teams_app.model.Team;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    private final JdbcPokemonDao pokemonDao;
    public PokemonController(JdbcPokemonDao pokemonDao) { this.pokemonDao = pokemonDao; }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Pokemon get(@PathVariable int id) {
        Pokemon pokemon = pokemonDao.getPokemonById(id);

        if(pokemon == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found.");
        } else {
            return pokemon;
        }
    }
}
