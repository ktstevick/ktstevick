<template>
    <div id="match-table">
        <!-- This needs to be reconceptualized -->
        <div class="match" v-for="week in currentWeekNo">
             <!-- <div class="icon" v-for="icon in leftTeam">
                <img v-bind:src="icon" />
             </div> -->
             
            -- WEEK {{ week }} --

            <!-- <div class="icon" v-for="icon in rightTeam">
                <img v-bind:src="icon" />
             </div> -->
        </div>


    </div>
</template>

<script>
import pokemonService from '../services/pokemonService.js';

export default {
    props: {
        currentWeekNo: Number
    },

    data() {
        return {
            leftTeams: [], // leftTeams?
            rightTeams: []
        }
    },

    methods: {
        // getTeams() {
        //     let searchName;

        //     // leftTeam
        //     for (let i = 0; i < 6; i++) {
        //         if (this.$store.state.coaches[0].teams[0][i].includes("M-")) {
        //             searchName = this.$store.state.coaches[0].teams[0][i].substring(2) + "-mega";
        //         } else {
        //             searchName = this.$store.state.coaches[0].teams[0][i];
        //         }
                
        //         this.leftTeam.push(searchName);
        //     }

        //     for(let i = 0; i < this.leftTeam.length; i++) {
        //         pokemonService.getPokemonByName(this.leftTeam[i])
        //             .then((response) => {
        //                 this.leftTeam[i] = response.data.sprites.versions["generation-vii"].icons.front_default;
        //             })
        //     }

        //     //  rightTeam - I'll consolidate later
        //     for (let i = 0; i < 6; i++) {
        //         if (this.$store.state.coaches[1].teams[0][i].includes("M-")) {
        //             searchName = this.$store.state.coaches[1].teams[0][i].substring(2) + "-mega";
        //         } else {
        //             searchName = this.$store.state.coaches[1].teams[0][i];
        //         }
                
        //         this.rightTeam.push(searchName);
        //     }

        //     for(let i = 0; i < this.rightTeam.length; i++) {
        //         pokemonService.getPokemonByName(this.rightTeam[i])
        //             .then((response) => {
        //                 this.rightTeam[i] = response.data.sprites.versions["generation-vii"].icons.front_default;
        //             })
        //     }
        // }

        getIndividualTeam(rosterArray) {
            const team = [];

            for(let i = 0; i < rosterArray.length; i++) {
                pokemonService.getPokemonByName(rosterArray[i])
                    .then((response) => {
                        team.push(response.data.sprites.versions["generation-vii"].icons.front_default);
                    })
            }
        }

        // Method to replace string names with icons directly?
    },

    // created() {
    //     this.getTeams();
    // }
}
</script>

<style scoped>
#match-table {
    color: white;
    background-color: rgb(30,40,30);
}

.button {
    background-color: rgb(70,100,70);
    border-radius: 5px;
}

.preview {
    display: inline;
}

.match {
    display: flex;

    height: 6vh;
    align-items: center;
    justify-content: center;
}

img {
    width: 35px;
}
</style>