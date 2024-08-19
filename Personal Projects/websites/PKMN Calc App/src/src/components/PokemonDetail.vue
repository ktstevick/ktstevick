<template>
    <div id="detail">
        <div id="hero"> <!-- Button implementation will likely involve icons -->
            <h1>{{ displayID }}</h1>
            <img :src="img" @click="this.$router.push({ name: 'calc', params: { id: this.id } })" />
            <h1>{{ name }}</h1>
        </div>

        <div id="info">
            <div>
                <h2>Stats:</h2>
                <p>HP: {{ stats[0] }} </p>
                <p>Atk: {{ stats[1] }} </p>
                <p>Def: {{ stats[2] }} </p>
                <p>SpA: {{ stats[3] }} </p>
                <p>SpD: {{ stats[4] }} </p>
                <p>Speed: {{ stats[5] }} </p>
            </div>
            <div>
                <h2>Abilities:</h2>
                <li v-for="ability in abilitiesArray" :key="ability.id">
                    {{ ability.name }}
                </li>
            </div>
        </div>

        <div id="moveList">
            <h2>Moves:</h2>
            <li class="moveItem" v-for="move in movesArray" v-bind:key="move">
                {{ move }}
            </li>
        </div>

        <div id="gallery">
            <h2>Gallery:</h2>
            <div v-for="generation in galleryArray" :key="generation">
                <hr>
                <div v-for="game in generation.games" :key="game">
                    <img v-for="sprite in game.sprites" :src="sprite" :key="sprite">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import pokemonService from '@/services/pokemonService';
import utilityTool from "../misc/utilityTool.js";

export default {
    name: "pokemon-detail",

    props: {
        id: Number
    },

    data() {
        return {
            name: "",
            displayID: "",
            img: "",
            stats: [
                0,
                0,
                0,
                0,
                0,
                0
            ],

            abilitiesArray: [],
            movesArray: [],
            galleryArray: []
        }
    },

    methods: {
        getDetails() {
            pokemonService.getPokemonDetail(this.id)
                .then((response) => {
                    this.name = utilityTool.nameFormatter(response.data.name);
                    this.img = response.data.sprites.front_default;

                    for (let i = 0; i < response.data.stats.length; i++) {
                        this.stats[i] = response.data.stats[i].base_stat;
                    }

                    for (let i = 0; i < response.data.abilities.length; i++) {
                        const abilityObj = {};
                        abilityObj.name = utilityTool.nameFormatter(response.data.abilities[i].ability.name);
                        abilityObj.id = utilityTool.abilityIdSelector(response.data.abilities[i].ability.url);

                        this.abilitiesArray[i] = abilityObj;
                    }

                    for (let i = 0; i < response.data.moves.length; i++) {
                        this.movesArray.push(utilityTool.nameFormatter(response.data.moves[i].move.name));
                    }
                
                    for(let genKey in response.data.sprites.versions) {
                        let generationObj = {};
                        generationObj.games = [];

                        for(let gameKey in response.data.sprites.versions[genKey]) {
                            let thisGame = response.data.sprites.versions[genKey][gameKey];
                            let gameObj = {};
                            gameObj.sprites = [];

                            for(let spriteKey in thisGame) {
                                gameObj.sprites.push(thisGame[spriteKey]);
                            }

                            generationObj.games.push(gameObj);
                        }

                        this.galleryArray.push(generationObj);
                    }
                })
        }
    },

    created() {
        this.displayID = utilityTool.indexFormatter(this.id);
        this.getDetails();
    }
}
</script>

<style scoped>
#hero {
    background-color: rgb(255, 184, 78);

    margin-top: 10px;
    border-top-right-radius: 10px;
    border-top-left-radius: 10px;

    display: flex;
    align-items: flex-end;
}

#hero img:hover {
    cursor: pointer;
}

#info {
    background-color: rgba(255, 184, 78, 0.75);

    border-bottom-right-radius: 10px;
    border-bottom-left-radius: 10px;

    display: grid;
    grid-template-columns: 1fr 1fr;
}

#moveList {
    background-color: rgba(255, 184, 78, 0.5);
    border-radius: 10px;
}

#gallery {
    margin: 10px;
    border: 5px solid orange;
    border-radius: 10px;
    padding: 10px 25px;
}

@media only screen and (max-width: 768px) {
    #info {
        display: block;
    }
}
</style>