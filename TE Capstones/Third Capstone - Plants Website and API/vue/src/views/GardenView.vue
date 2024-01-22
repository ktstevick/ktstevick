<template>
    <h2>My Garden:</h2>
    <button v-if="this.garden.garden_type == 'Community'">Volunteer To Help Out</button>
    <div id="gardenDisplay" v-for="plant in this.gardenPlant" v-bind:key="plant.id">
      <garden-card v-bind:plant="plant" v-if="plant.is_active"/>
    </div>
</template>

<script>
    import gardenCard from '../components/GardenCard.vue';
    import plantService from '../services/PlantService.js';

    export default { 
        data() {
            return { 
                garden: {},
                gardenPlant: [],
                plant: {}
            }
        },
        
        components: {
            gardenCard
        },

        methods: {
            getGardenById(id) {
                plantService.getPlantsByGarden(id).then( (response) => {
                    this.gardenPlant = response.data;
                })
            },
            getGarden(id) {
                plantService.getGardenById(id).then(response => {
                    this.garden = response.data;
                })
            }
        },

        created() {
            this.getGardenById(this.$route.params.id);
            this.getGarden(this.$route.params.id);

            plantService.getGardenByUserId(this.$store.state.user.id)
            .then( response => {
                this.gardenArray = response.data;
                this.$store.commit('SET_USER_GARDEN', this.gardenArray[0]);
        
                plantService.getPlantsByGarden(this.$store.state.user_garden.garden_id)
                    .then( response => {
                    this.$store.commit('SET_USER_PLANTS', response.data);
                });
            });
        }
    };
</script>

<style scoped>
#gardenDisplay {
    display: flex;
}


@media screen and (min-width: 600px) {
    #gardenDisplay {
        display: inline-flex;
        width: 32vw;
    }
}

@media screen and (min-width: 992px) {
    #gardenDisplay {
        display: inline-flex;
        width: 19vw;
    }
}
</style>