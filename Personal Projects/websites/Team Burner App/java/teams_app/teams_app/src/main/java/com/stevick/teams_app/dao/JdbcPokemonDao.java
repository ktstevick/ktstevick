package com.stevick.teams_app.dao;

import com.stevick.teams_app.exception.DaoException;
import com.stevick.teams_app.model.Pokemon;
import com.stevick.teams_app.model.Team;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcPokemonDao implements PokemonDao{
        private final JdbcTemplate jdbcTemplate;
        private String sql;

        public JdbcPokemonDao (JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Pokemon getPokemonById(int pokemonId) {
            Pokemon pokemon = null;
            String sql = "SELECT * FROM pokemon WHERE pokemon_id = ?;";

            try {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pokemonId);
                while (results.next()) {
                    pokemon = mapRowToPokemon(results);
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect somehow!", e);
            }

            return pokemon;
        }

    private Pokemon mapRowToPokemon(SqlRowSet rs) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(rs.getInt("pokemon_id"));
        pokemon.setTeamId(rs.getInt("team_id"));
        pokemon.setName(rs.getString("name"));
        pokemon.setNickName(rs.getString("nickname"));

        pokemon.setItem(rs.getString("item"));
        pokemon.setAbility(rs.getString("name"));
        pokemon.setTeraType(rs.getString("tera_type"));

        pokemon.setEVs(rs.getString("evs"));
        pokemon.setIVs(rs.getString("ivs"));
        pokemon.setNature(rs.getString("nature"));

        pokemon.setMove1(rs.getString("move_1"));
        pokemon.setMove2(rs.getString("move_2"));
        pokemon.setMove3(rs.getString("move_3"));
        pokemon.setMove4(rs.getString("move_4"));

        pokemon.setShiny(rs.getBoolean("is_shiny"));

        return pokemon;
    }
}
