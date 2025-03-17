import axios from 'axios';

const http = axios.create({
    baseURL: 'https://pokeapi.co/api/v2/'
});

export default {
    // POKEMON
    getPokemonByID(id) {
        return http.get(`pokemon/${id}`);
    },

    getPokemonByName(name) { 
        return http.get(`pokemon/${name}`);
    },

    getAllPokemon() {
        return http.get('pokemon/?offset=0&limit=1025'); // Goes up to 1302, regional variants are ID'ed from 10000
    }
}