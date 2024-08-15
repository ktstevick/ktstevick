import axios from 'axios';

const http = axios.create({
    baseURL: 'https://pokeapi.co/api/v2/'
});

export default {
    // POKEMON
    getAllPokemon() {
        return http.get('pokemon/?offset=0&limit=1025'); // Goes up to 1302, regional variants are ID'ed from 10000
    },
    getFirstGen() {
        return http.get('pokemon/?offset=0&limit=151');
    },
    getPokemonDetail(id) {
        return http.get(`pokemon/${id}`);
    },

    // MOVES
    getMoveDetail(id) {
        return http.get(`move/${id}`);
    },

    // MISC
    getAllNatures() {
        return http.get('nature?offset=0&limit=25');
    }
}