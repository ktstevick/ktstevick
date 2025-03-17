<!-- Be mindful; this will likely prove more versatile via store -->
<template>
    <div id="overview">
        <div id="hero">
            <img v-bind:src="this.$store.state.coaches[trainerID].avatar" />
            <h1>{{ this.$store.state.coaches[trainerID].name }}</h1>
        </div>

        <div id="roster">
            <div class="icon" v-for="icon in this.rosterIcons">
                <img v-bind:src="icon" />
            </div>
        </div>

        <!-- Make these collapsable later -->
        <p>{{ this.$store.state.coaches[trainerID].summary }}</p>
    </div>
</template>

<script>
import pokemonService from '../services/pokemonService.js';

export default {
    props: {
        trainerID: Number
    },

    data() {
        return {
            rosterIcons: []
        }
    },

    methods: {
        getRosterIcons() {
            let searchName;

            // Form failsafe
            for (let i = 0; i < this.$store.state.coaches[this.trainerID].roster.length; i++) {
                if (this.$store.state.coaches[this.trainerID].roster[i].includes("M-")) {
                    searchName = this.$store.state.coaches[this.trainerID].roster[i].substring(2) + "-mega";
                } else {
                    searchName = this.$store.state.coaches[this.trainerID].roster[i];
                }
                
                this.rosterIcons.push(searchName);
            }

            const results = [];

            // Actual icon assignment
            for(let i = 0; i < this.rosterIcons.length; i++) {
                pokemonService.getPokemonByName(this.rosterIcons[i])
                    .then((response) => {
                        this.rosterIcons[i] = response.data.sprites.versions["generation-vii"].icons.front_default;
                    })
            }
        }
    },

    created() {
        this.getRosterIcons();
    }
}

</script>

<style scoped>
#overview {
    background-color: rgb(31, 33, 31);

    width: 80vw;

    margin: 5vh auto;
    padding: 10px;
    border: 4px solid black;
    border-radius: 25px;
}

#hero {
    display: flex;
    margin: 1vh auto;
}

h1 {
    font-size: 2.2em;
    margin: auto 0px;
}

#roster {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
}

.icon {
    background-color: rgb(255, 200, 200);

    height: 45px;
    width: 45px;

    margin: 5px;

    border-radius: 25px;
    border: 3px solid white;

    display: inline-block;
}
</style>