<template>
    <main>
        <div id="list">
            <div id="search">
                <input name="searchBar" type="text" v-model="searchParam">
                <div>
                    <button @click="filterPokemon">Name Search</button>
                    <button @click="feelingLucky">I'm Feeling Lucky</button>
                    <!-- <button @click="toggleDisplay">{{ toggleText }}...</button> -->
                </div>
            </div>

            <!-- <div id="controls" v-if="visibleControls">
                <p>BEHOLD ADVANCED OPTIONS</p>
                <input type="checkbox">
                <input type="checkbox">
                <input type="checkbox">
            </div> -->

            <div id="cards">
                <pokemonPreviewCard v-for="pokemon in displayArray" :key="pokemon.id" :id="pokemon.id"
                    @click="this.$router.push({ name: 'detail', params: { id: pokemon.id } })" />
            </div>
        </div>
    </main>
</template>

<script>
import pokemonService from "../services/pokemonService.js";
import pokemonPreviewCard from "../components/PokemonPreviewCard.vue";
import utilityTool from "../misc/utilityTool.js";

export default {
    name: "pokemon-list",

    components: {
        pokemonPreviewCard
    },

    data() {
        return {
            pokemonArray: [],
            displayArray: [],

            searchParam: '',

            toggleText: 'More',
            visibleControls: false
        }
    },

    methods: {
        getPokemon() {
            pokemonService.getFirstGen() // Initial display
                .then((response) => {
                    let temp = response.data.results;
                    this.displayArray = temp.map((item) => {
                        item.id = utilityTool.pokemonIdSelector(item.url);
                        return item;
                    })
                });

            pokemonService.getAllPokemon() // Full list
                .then((response) => {
                    let temp = response.data.results;
                    this.pokemonArray = temp.map((item) => {
                        item.id = utilityTool.pokemonIdSelector(item.url);
                        return item;
                    })
                })
        },

        filterPokemon() {
            if (this.searchParam !== '') { // Quick check for blank search bar
                const results = this.pokemonArray.filter(pokemon => {
                    return pokemon.name.indexOf(this.searchParam) > -1;
                })

                this.displayArray = results;
            } else {
                this.displayArray = [];

                for (let i = 0; i < 151; i++) { // Initial display size
                    this.displayArray[i] = this.pokemonArray[i];
                }

                alert('You need to enter text to search!');
            }
        },

        feelingLucky() {
            let dexNumber = Math.floor(Math.random() * this.pokemonArray.length - 1);
            this.$router.push({ name: 'detail', params: { id: dexNumber } });
        },

        // toggleDisplay() {
        //     this.visibleControls = !this.visibleControls;

        //     if(this.toggleText === 'More') {
        //         this.toggleText = 'Less';
        //     } else {
        //         this.toggleText = 'More';
        //     }
        // }
    },

    created() {
        this.getPokemon();
    }
}
</script>

<style scoped>
main {
    text-align: center;
}

input {
    width: 50vw;
}

#search {
    margin: 7.5vw; /* Scales to width */
}

#cards {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;

    width: 75vw;
    margin-left: 11vw;
}
</style>