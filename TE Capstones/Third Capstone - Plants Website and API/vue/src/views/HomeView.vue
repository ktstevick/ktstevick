<template>
  <!-- <div class="home">
    <p>Hello, {{ this.$store.state.user.username }}'s homepage</p>
  </div> -->

  <div class="gardenCard" v-for="garden in this.gardenArray" v-bind:key="garden.garden_id">
    <h2>View My Garden:</h2>
    <img class="cardImage" src="../assets/garden.jpg"
      v-on:click="this.$router.push({ name: 'gardenView', params: { id: garden.garden_id } });" />
  </div>
</template>

<script>
import plantService from '../services/PlantService.js';
import messageService from '../services/MessageService.js';


export default {
  data() {
    return {
      gardenArray: [],
      defaultGarden: {},
    }
  },

  created() {

    plantService.getGardenByUserId(this.$store.state.user.id)
      .then(response => {
        this.gardenArray = response.data;
        this.$store.commit('SET_USER_GARDEN', this.gardenArray[0]);

        plantService.getPlantsByGarden(this.$store.state.user_garden.garden_id)
          .then(response => {
            this.$store.commit('SET_USER_PLANTS', response.data);
          });
      });

    messageService.getUserMessages(this.$store.state.user.id)
      .then(response => {
        this.$store.commit('SET_USER_MESSAGES', response.data)
      })
  }
};
</script>

<style scoped>
h2 {
  text-align: center;
}
.cardImage {
  border-top: 4px solid rgb(23, 63, 22);
  border-right: 4px solid rgb(23, 63, 22);
  border-left: 4px solid rgb(72, 128, 71);
  border-bottom: 4px solid rgb(72, 128, 71);
  border-radius: 5px;

  width: 70vw;
}

@media screen and (min-width: 600px) {

  h2 {
  text-align: left;
}

  .cardImage {
    width: 50vw;
  }
}

</style>