<template>
  <main>
    <div id="custom1moves">
      <div @click="this.assignCurrent(0, 0)">
        <p>{{ this.$store.state.pokemon[0].displayMoves[0].name }}:</p>
        <p>{{ pkmn0move0 }}</p>
      </div>
      <div @click="this.assignCurrent(0, 1)">
        <p>{{ this.$store.state.pokemon[0].displayMoves[1].name }}:</p>
        <p>{{ pkmn0move1 }}</p>
      </div>
      <div @click="this.assignCurrent(0, 2)">
        <p>{{ this.$store.state.pokemon[0].displayMoves[2].name }}:</p>
        <p>{{ pkmn0move2 }}</p>
      </div>
      <div @click="this.assignCurrent(0, 3)">
        <p>{{ this.$store.state.pokemon[0].displayMoves[3].name }}:</p>
        <p>{{ pkmn0move3 }}</p>
      </div>
    </div>

    <div id="custom2moves">
      <div @click="this.assignCurrent(1, 0)">
        <p>{{ this.$store.state.pokemon[1].displayMoves[0].name }}:</p>
        <p>{{ pkmn1move0 }}</p>
      </div>
      <div @click="this.assignCurrent(1, 1)">
        <p>{{ this.$store.state.pokemon[1].displayMoves[1].name }}:</p>
        <p>{{ pkmn1move1 }}</p>
      </div>
      <div @click="this.assignCurrent(1, 2)">
        <p>{{ this.$store.state.pokemon[1].displayMoves[2].name }}:</p>
        <p>{{ pkmn1move2 }}</p>
      </div>
      <div @click="this.assignCurrent(1, 3)">
        <p>{{ this.$store.state.pokemon[1].displayMoves[3].name }}:</p>
        <p>{{ pkmn1move3 }}</p>
      </div>
    </div>

    <div id="description">
      <h2>{{ moveDescription }}</h2>
      <p>Possible Damage Rolls: {{ damageRolls }}</p>
    </div>

    <div id="custom1">
      <customPKMN :id="this.customID" />
    </div>
    <div id="custom2">
      <customPKMN :id=4 />
    </div>

  </main>
</template>

<script>
import utilityTool from '@/misc/utilityTool';
import customPKMN from './CustomPKMN.vue';

export default {
  data() {
    return {
      currentPokemon: 0,
      currentMove: 0,
      smallScreen: true
    }
  },

  props: {
    customID: Number // THIS DATA TYPE ISSUES WARNINGS, NEEDS ADJUSTMENT
  },

  components: {
    customPKMN
  },

  computed: {
    pkmn0move0() {
      return this.computedDamageEstimate(0, 0);
    },
    pkmn0move1() {
      return this.computedDamageEstimate(0, 1);
    },
    pkmn0move2() {
      return this.computedDamageEstimate(0, 2);
    },
    pkmn0move3() {
      return this.computedDamageEstimate(0, 3);
    },

    pkmn1move0() {
      return this.computedDamageEstimate(1, 0);
    },
    pkmn1move1() {
      return this.computedDamageEstimate(1, 1);
    },
    pkmn1move2() {
      return this.computedDamageEstimate(1, 2);
    },
    pkmn1move3() {
      return this.computedDamageEstimate(1, 3);
    },

    damageRolls() {
      let result = this.getDamageArray(this.currentPokemon, this.currentMove);

      if (result[0] <= 1) { // Status Moves (et alia)
        result = '';
      }

      return result;
    },

    moveDescription() {
      return this.getMoveDescription(this.currentPokemon, this.currentMove);
    },
  },

  methods: {
    getDamageArray(attackerIndex, moveIndex) {
      let categories = utilityTool.categoryReference(this.$store.state.pokemon[attackerIndex].displayMoves[moveIndex].category);
      let defenderIndex = 1;
      if (attackerIndex === 1) {
        defenderIndex = 0;
      }

      let natureMultiplier = utilityTool.getNatureMultiplier(this.$store.state.pokemon[attackerIndex].nature, categories[0]);
      let aStat = utilityTool.basicStatCalc(this.$store.state.pokemon[attackerIndex].stats[categories[0]], this.$store.state.pokemon[attackerIndex].EVs[categories[0]], natureMultiplier);

      natureMultiplier = utilityTool.getNatureMultiplier(this.$store.state.pokemon[defenderIndex].nature, categories[1]);
      let dStat = utilityTool.basicStatCalc(this.$store.state.pokemon[defenderIndex].stats[categories[1]], this.$store.state.pokemon[defenderIndex].EVs[categories[1]], natureMultiplier);

      let move = this.$store.state.pokemon[attackerIndex].displayMoves[moveIndex];

      let typeMultiplier = utilityTool.getTypeMultiplier(move.type, this.$store.state.pokemon[defenderIndex].types[0], this.$store.state.pokemon[defenderIndex].types[1]);
      return utilityTool.damageCalc(move, aStat, dStat, typeMultiplier);
    },

    computedDamageEstimate(attackerIndex, moveIndex) {
      let defenderIndex = 1;
      if (attackerIndex === 1) {
        defenderIndex = 0;
      }

      let rollArray = this.getDamageArray(attackerIndex, moveIndex);

      let defenderHP = utilityTool.hpStatCalc(this.$store.state.pokemon[defenderIndex].stats[0], this.$store.state.pokemon[defenderIndex].EVs[0]);
      let minRoll = utilityTool.percentFix((rollArray[0] / defenderHP) * 100) + "%";
      let maxRoll = utilityTool.percentFix((rollArray[15] / defenderHP) * 100) + "%";

      let result = minRoll + ' - ' + maxRoll;

      if (this.$store.state.pokemon[attackerIndex].displayMoves[moveIndex].category === "Status") {
        result = '0.0% - 0.0%';
      }

      return result;
    },

    getMoveDescription(currentPokemon, currentMove) {
      let result = '';

      let defenderIndex = 1;
      if (currentPokemon === 1) {
        defenderIndex = 0;
      }

      let attackerNature = this.$store.state.pokemon[currentPokemon].nature;
      let attackerStr = this.$store.state.pokemon[currentPokemon].name + "'s " + this.$store.state.pokemon[currentPokemon].displayMoves[currentMove].name;
      let defenderNature = this.$store.state.pokemon[defenderIndex].nature;
      let defenderStr = this.$store.state.pokemon[defenderIndex].name;
      let percentStr = this.computedDamageEstimate(currentPokemon, currentMove);

      result = attackerNature + ' ' + attackerStr + ' vs ' + defenderNature + ' ' + defenderStr + ': ' + percentStr;

      return result;
    },

    assignCurrent(pokemonNo, moveNo) {
      this.currentPokemon = pokemonNo;
      this.currentMove = moveNo;
    },
  }
}
</script>

<style scoped>
main {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "custom1moves custom2moves"
    "description description"
    "custom1 custom2";

  width: 80vw;
  margin-left: 12vw;
  padding-top: 3vh;
  text-align: center;
}

#custom1moves {
  background-color: rgb(218, 250, 240);

  border-radius: 10px;
  width: 35vw;

  grid-area: custom1moves;
}

#custom1moves div {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

#custom1moves div:hover {
  cursor: pointer;
  background-color:  rgb(165, 199, 188);
}

#custom2moves {
  background-color: rgb(250, 228, 218);

  border-radius: 10px;
  width: 35vw;

  grid-area: custom2moves;
}

#custom2moves div {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

#custom2moves div:hover {
  cursor: pointer;
  background-color: rgb(207, 175, 160);
}

#description {
  font-weight: bold;
  grid-area: description;
  text-align: left;
}

#custom1 {
  grid-area: custom1;
  width: 35vw;
}

#custom2 {
  grid-area: custom2;
  width: 35vw;
}

@media only screen and (max-width: 768px) {
  main {
    display: grid;
    grid-template-areas: 
      "custom1"
      "custom1moves"
      "description"
      "custom2moves"
      "custom2"
  }

  #custom1 {
    width: 75vw;
  }
  #custom1moves {
    width: 75vw;
  }

  #custom2 {
    width: 75vw;
  }
  #custom2moves {
    width: 75vw;
  }

  #description {
    display: none;
  }
}
</style>