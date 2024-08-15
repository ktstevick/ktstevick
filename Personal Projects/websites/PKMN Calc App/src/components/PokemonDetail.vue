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
            </div>
            <div>
                <h2>Abilities:</h2>
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
            <img :src="img">
        </div>

        <div id="seeAlso">
            <h2>See Also:</h2>
            <p>Placeholder text - this will link to other Pokemon or searches</p>
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

            movesArray: []
        }
    },

    methods: {
        getDetails() {
            pokemonService.getPokemonDetail(this.id)
                .then((response) => {
                    this.name = utilityTool.nameFormatter(response.data.name);
                    this.img = response.data.sprites.front_default;

                    for (let i = 0; i < response.data.moves.length; i++) {
                        this.movesArray.push(utilityTool.nameFormatter(response.data.moves[i].move.name));
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

    border-radius: 10px;

    display: flex;
    align-items: flex-end;
}

#hero img:hover {
    cursor: pointer;
}

#info {
    background-color: rgba(255, 184, 78, 0.75);
    display: grid;
    grid-template-columns: 1fr 1fr;
}

#moveList {
    background-color: rgba(255, 184, 78, 0.5);
}

#gallery {
    background-color: rgba(255, 184, 78, 0.25);
}

#seeAlso {
    background-color: rgba(255, 184, 78, 0.1);
}

@media only screen and (max-width: 768px) {
    #info {
        display: block;
    }
}
</style>