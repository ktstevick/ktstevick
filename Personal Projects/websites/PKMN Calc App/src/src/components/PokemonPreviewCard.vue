<template>
    <main>
        <div class="cardBody" v-bind:class="{ // CSS
            'normal': this.type1 === 'Normal', 'fighting': this.type1 === 'Fighting', 'flying': this.type1 === 'Flying',
            'poison': this.type1 === 'Poison', 'ground': this.type1 === 'Ground', 'rock': this.type1 === 'Rock', 'bug': this.type1 === 'Bug',
            'ghost': this.type1 === 'Ghost', 'steel': this.type1 === 'Steel', 'fire': this.type1 === 'Fire', 'water': this.type1 === 'Water',
            'grass': this.type1 === 'Grass', 'electric': this.type1 === 'Electric', 'psychic': this.type1 === 'Psychic', 'ice': this.type1 === 'Ice',
            'dragon': this.type1 === 'Dragon', 'dark': this.type1 === 'Dark', 'fairy': this.type1 === 'Fairy' }">

            <p>{{ displayID }}</p>
            <img :src="img" />
            <p>{{ name }}</p>
            <p>{{ type1 }}{{ type2 }}</p>
        </div>
    </main>
</template>

<script>
import pokemonService from '../services/pokemonService.js';
import utilityTool from "../misc/utilityTool.js";

export default {
    name: "pokemon-preview",

    props: {
        id: Number
    },

    data() {
        return {
            name: '',
            displayID: 'text',
            img: '',

            type1: '',
            type2: ''
        }
    },

    created() {
        pokemonService.getPokemonDetail(this.id)
            .then((response) => {
                this.name = utilityTool.nameFormatter(response.data.name);
                this.displayID = utilityTool.indexFormatter(response.data.id);
                this.img = response.data.sprites.versions["generation-viii"].icons.front_default;

                this.type1 = utilityTool.nameFormatter(response.data.types[0].type.name);
                if (response.data.types.length > 1) {
                    this.type2 = ' / ' + utilityTool.nameFormatter(response.data.types[1].type.name);
                }
            })
    }
}
</script>

<style scoped>
main {
    background-color: rgb(246, 249, 255);

    -webkit-transition: background-color 500ms;
    -moz-transition: background-color 500ms;
    -o-transition: background-color 500ms;
    transition: background-color 500ms;

    width: 170px;
    margin: 1vh 0.5vw;
    border-radius: 5px;
}

main:hover {
    cursor: pointer;
    background-color: gray;
}

p {
    padding: .5vh;
}

.normal {
    background-color: rgb(230, 230, 230);
}
.fighting {
    background-color: rgb(255, 227, 190);
}
.flying {
    background-color: rgb(233, 243, 255);
}
.poison {
    background-color: rgb(242, 223, 255);
}
.ground {
    background-color: rgb(255, 213, 181);
}
.rock {
    background-color: rgb(255, 250, 221);
}
.bug {
    background-color: rgb(248, 255, 192);
}
.ghost {
    background-color: rgb(216, 190, 216);
}
.steel {
    background-color: rgb(192, 192, 192);
}
.fire {
    background-color: rgb(255, 218, 207);
}
.water {
    background-color: rgb(222, 234, 255);
}
.grass {
    background-color: rgb(217, 255, 204);
}
.electric {
    background-color: rgb(255, 242, 174);
}
.psychic {
    background-color: rgb(255, 207, 219);
}
.ice {
    background-color: rgb(178, 235, 255);
}
.dragon {
    background-color: rgb(198, 201, 255);
}
.dark {
    background-color: rgb(151, 151, 151);
}
.fairy {
    background-color: rgb(255, 215, 252);
}

@media only screen and (max-width: 768px) {
    main {
        width: 75vw;
    }
}
</style>