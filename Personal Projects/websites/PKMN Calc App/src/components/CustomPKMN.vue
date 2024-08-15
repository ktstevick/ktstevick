<template>
    <div id="customPokemon">
        <select name="pokemonSelect" v-model="this.pokemon.dexNumber" @click="updatePokemonChoice">
            <option disabled value="">X-- Choose Pokemon --X</option>
            <option v-for="pokemon in pokemonChoices" :value="pokemon.id" :key="pokemon.id">
                {{ pokemon.name }}
            </option>
        </select>

        <div id="hero">
            <img :src="img" alt=""
                @click="this.$router.push({ name: 'detail', params: { id: this.pokemon.dexNumber } })" />
        </div>

        <div id="dataBox">
            <p>{{ pokemon.types[0] }} {{ pokemon.types[1] }}</p>

            <div id="abilitySelect">
                <select v-model="this.pokemon.ability">
                    <option v-for="ability in abilityChoices" :value="ability" :key="ability.id">
                        {{ ability.name }}
                    </option>
                </select>
            </div>

            <div id="natureSelect" @click="updateStore">
                <select v-model="this.pokemon.nature">
                    <option v-for="nature in natureChoices" :value="nature.name" :key="nature.id">
                        {{ nature.name }}
                    </option>
                </select>
            </div>
        </div>

        <div id="statsBox">
            <div>
                <p>HP: {{ pokemon.stats[0] }}</p>
                <input name="hpEvs" type="number" min="0" max="252" step="4" v-model="this.pokemon.EVs[0]">
                <p>{{ actualStatHP }}</p>
            </div>
            <div>
                <p>Atk: {{ pokemon.stats[1] }}</p>
                <input name="atkEvs" type="number" min="0" max="252" step="4" v-model="this.pokemon.EVs[1]">
                <p>{{ actualStatAtk }}</p>
            </div>
            <div>
                <p>Def: {{ pokemon.stats[2] }}</p>
                <input name="defEvs" type="number" min="0" max="252" step="4" v-model="this.pokemon.EVs[2]">
                <p>{{ actualStatDef }}</p>
            </div>
            <div>
                <p>SpAtk: {{ pokemon.stats[3] }}</p>
                <input name="spAtkEvs" type="number" min="0" max="252" step="4" v-model="this.pokemon.EVs[3]">
                <p>{{ actualStatSpA }}</p>
            </div>
            <div>
                <p>SpDef: {{ pokemon.stats[4] }}</p>
                <input name="spDefEvs" type="number" min="0" max="252" step="4" v-model="this.pokemon.EVs[4]">
                <p>{{ actualStatSpD }}</p>
            </div>
            <div>
                <p>Speed: {{ pokemon.stats[5] }}</p>
                <input name="speedEvs" type="number" min="0" max="252" step="4" v-model="this.pokemon.EVs[5]">
                <p>{{ actualStatSpeed }}</p>
            </div>

        </div>

        <div id="movesBox">
            <div class="moveSelect">
                <select v-model="this.pokemon.displayMoves[0]">
                    <option v-for="move in this.pokemon.totalMoves" :value="move" :key="move.url">{{ move.name }}
                    </option>
                </select>
                
            </div>
            <div class="moveSelect">
                <select v-model="this.pokemon.displayMoves[1]">
                    <option v-for="move in this.pokemon.totalMoves" :value="move" :key="move.url">{{ move.name }}
                    </option>
                </select>
            </div>
            <div class="moveSelect">
                <select v-model="this.pokemon.displayMoves[2]">
                    <option v-for="move in this.pokemon.totalMoves" :value="move" :key="move.url">{{ move.name }}
                    </option>
                </select>
            </div>
            <div class="moveSelect">
                <select v-model="this.pokemon.displayMoves[3]">
                    <option v-for="move in this.pokemon.totalMoves" :value="move" :key="move.url">{{ move.name }}
                    </option>
                </select>
            </div>
        </div>
    </div>
</template>

<script>
import pokemonService from "../services/pokemonService.js";
import utilityTool from "../misc/utilityTool.js";

export default {
    props: {
        id: Number
    },

    data() {
        return {
            pokemon: {
                dexNumber: 1,
                name: "",
                img: "",

                types: [],
                stats: [],
                EVs: [
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
                ],

                ability: '',
                nature: 'Serious',

                totalMoves: [],
                displayMoves: [
                    { type: "", power: "", category: "" },
                    { type: "", power: "", category: "" },
                    { type: "", power: "", category: "" },
                    { type: "", power: "", category: "" }
                ]
            },

            pokemonChoices: [],
            natureChoices: [],
            abilityChoices: []
        }
    },

    computed: {
        actualStatHP() {
            const HP_INDEX = 0;
            return utilityTool.hpStatCalc(this.pokemon.stats[HP_INDEX], this.pokemon.EVs[HP_INDEX]);
        },
        actualStatAtk() {
            const ATK_INDEX = 1;
            return utilityTool.basicStatCalc(this.pokemon.stats[ATK_INDEX], this.pokemon.EVs[ATK_INDEX], utilityTool.getNatureMultiplier(this.pokemon.nature, ATK_INDEX));
        },
        actualStatDef() {
            const DEF_INDEX = 2;
            return utilityTool.basicStatCalc(this.pokemon.stats[DEF_INDEX], this.pokemon.EVs[DEF_INDEX], utilityTool.getNatureMultiplier(this.pokemon.nature, DEF_INDEX));
        },
        actualStatSpA() {
            const SPA_INDEX = 3;
            return utilityTool.basicStatCalc(this.pokemon.stats[SPA_INDEX], this.pokemon.EVs[SPA_INDEX], utilityTool.getNatureMultiplier(this.pokemon.nature, SPA_INDEX));
        },
        actualStatSpD() {
            const SPD_INDEX = 4;
            return utilityTool.basicStatCalc(this.pokemon.stats[SPD_INDEX], this.pokemon.EVs[SPD_INDEX], utilityTool.getNatureMultiplier(this.pokemon.nature, SPD_INDEX));
        },
        actualStatSpeed() {
            const SPEED_INDEX = 5;
            return utilityTool.basicStatCalc(this.pokemon.stats[SPEED_INDEX], this.pokemon.EVs[SPEED_INDEX], utilityTool.getNatureMultiplier(this.pokemon.nature, SPEED_INDEX));
        }
    },

    methods: {
        updateStore() {
            const shallowPokemon = { ...this.pokemon };
            shallowPokemon.componentId = this.id;
            this.$store.commit('UPDATE_POKEMON', shallowPokemon);
        },

        updatePokemonChoice() {
            // HOUSEKEEPING
            this.pokemon.abilities = [];
            this.pokemon.totalMoves = [];
            this.pokemon.displayMoves = [];

            // UPDATE
            pokemonService.getPokemonDetail(this.pokemon.dexNumber)
                .then((response) => {
                    this.pokemon.name = utilityTool.nameFormatter(response.data.name);
                    this.pokemon.img = response.data.sprites.front_default;
                    this.img = response.data.sprites.front_default;

                    this.pokemon.types[0] = utilityTool.nameFormatter(response.data.types[0].type.name);
                    this.pokemon.types[1] = "";
                    if (response.data.types.length > 1) {
                        this.pokemon.types[1] = utilityTool.nameFormatter(response.data.types[1].type.name);
                    }

                    for (let i = 0; i < response.data.stats.length; i++) {
                        this.pokemon.stats[i] = response.data.stats[i].base_stat;
                    }

                    for (let i = 0; i < response.data.abilities.length; i++) {
                        const abilityObj = {};
                        abilityObj.name = utilityTool.nameFormatter(response.data.abilities[i].ability.name);
                        abilityObj.id = utilityTool.abilityIdSelector(response.data.abilities[i].ability.url);

                        this.abilityChoices[i] = abilityObj;
                    }
                    this.pokemon.ability = this.abilityChoices[0];

                    for (let i = 0; i < response.data.moves.length; i++) {
                        let move = response.data.moves[i].move;
                        move.id = utilityTool.moveIdSelector(move.url);

                        pokemonService.getMoveDetail(move.id) // Async within loop
                            .then((response) => {
                                move.name = utilityTool.nameFormatter(response.data.name);
                                move.type = utilityTool.nameFormatter(response.data.type.name);
                                move.power = response.data.power;
                                move.category = utilityTool.nameFormatter(response.data.damage_class.name);

                                move.stab = false;
                                if (move.type === this.pokemon.types[0] || move.type === this.pokemon.types[1]) {
                                    move.stab = true;
                                }
                            })

                        this.pokemon.totalMoves[i] = move;
                    }

                    for (let i = 0; i < 4; i++) { // Always 4 moves on display
                        this.pokemon.displayMoves[i] = this.pokemon.totalMoves[i];
                    }

                    setTimeout(this.updateStore, 200); // Fifth of a second
                });

        }
    },

    created() {
        this.pokemon.dexNumber = this.id;

        this.updatePokemonChoice();

        pokemonService.getAllPokemon()
            .then((response) => {
                let temp = response.data.results;
                this.pokemonChoices = temp.map((item) => {
                    item.name = utilityTool.nameFormatter(item.name);
                    item.id = utilityTool.pokemonIdSelector(item.url);
                    return item;
                })
            });

        pokemonService.getAllNatures()
            .then((response) => {
                let temp = response.data.results;

                this.natureChoices = temp.map((item) => {
                    item.name = utilityTool.nameFormatter(item.name);
                    item.id = utilityTool.natureIdSelector(item.url);
                    return item;
                })
            });
    }
}
</script>

<style scoped>
#customPokemon {
    background-color: rgb(241, 238, 255);
    border-radius: 10px;
}

#statsBox {
    background-color: white;
}

#statsBox div {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
}

#statsBox div input {
    width: 10vw;
}

#movesBox {
    display: grid;
    width: 25vw;
}

.moveSelect {
    display: flex;
}

.moveSelect p {
    margin-left: 2vw;
    width: 8vw;
}
</style>