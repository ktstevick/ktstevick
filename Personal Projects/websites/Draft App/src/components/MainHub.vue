<template>
  <h2>Test</h2>
  <p>pokemonService is up and running! Currently just my own roster icons are fetched, but we're plugged in and ready to go.</p>
  <p>Back-end development necessary soon... but honestly I COULD just style for a while.</p>
  <hr>
  <h2>pokemonService test area:</h2>
  <div class="button" v-on:click="getPokemon">PRESS ME</div>
  <p>{{ modelPKMN.name }}</p>
  <img v-bind:src="modelPKMN.icon" />
  <img v-bind:src="modelPKMN.image" />
  <hr>

  <Overview :trainerID="0"/>

  <MatchTable :currentWeekNo="3"/>

  <MatchCard v-for="number in 3" :weekNo=number />

</template>

<script>
import pokemonService from '../services/pokemonService.js';

import Overview from './Overview.vue';
import MatchTable from './MatchTable.vue';
import MatchCard from './MatchCard.vue';

export default {
  components: {
    Overview,
    MatchTable,
    MatchCard
  },

  data() {
    return {
      modelPKMN: {
        name: '',
        icon: '',
        image: '',
      }
    }
  },

  methods: {
    getPokemon() {
      let searchFor = prompt("Search for Pokemon?", "25");

      if(isNaN(searchFor)) {
        pokemonService.getPokemonByName(searchFor)
        .then((response) => {
          this.modelPKMN.name = response.data.name;
          this.modelPKMN.icon = response.data.sprites.versions["generation-vii"].icons.front_default;
          this.modelPKMN.image = response.data.sprites.versions["generation-vii"]["ultra-sun-ultra-moon"].front_default;
        })

      } else {
        pokemonService.getPokemonByID(searchFor)
        .then((response) => {
          this.modelPKMN.name = response.data.name;
          this.modelPKMN.icon = response.data.sprites.versions["generation-vii"].icons.front_default;
          this.modelPKMN.image = response.data.sprites.versions["generation-vii"]["ultra-sun-ultra-moon"].front_default;
        })
      }
    }
  }
}

</script>

<style scoped>
.button {
  background-color: gray;
  width: 20vw;
  height: 5vh;
  text-align: center;
  font-size: 1.4em;
  border-radius: 5px;
  margin: 20px;
}
</style>
