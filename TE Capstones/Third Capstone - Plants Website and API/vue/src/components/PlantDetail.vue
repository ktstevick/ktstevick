<template>
    <div id="wholeView">
        <div id="plantDetails">
            <h2>{{ this.plant.common_name }}</h2>
            <h2 class="scientificName">{{ this.plant.scientific_name }}</h2>

            <img v-if="plant.default_image === null" v-bind:src="getDefaultImage" />
            <img v-else-if="plant.default_image != null" v-bind:src="plant.default_image.original_url" id="displayImage" />
        </div>

        <div id="careDetails">
            <p>{{ plant.description }}</p>

            <h3>Cycle: {{ plant.cycle }}</h3>
            <h3>Watering: {{ plant.watering }}</h3>
            <h3>Sunlight: {{ plant.sunlight }}</h3>

            <div id="addButton">
                <button v-on:click="onAddClick">ADD TO GARDEN</button>
            </div>
            <div id="gardenButton">
                <button v-on:click="onGardenClick">MY GARDEN</button>
            </div>
        </div>
    </div>
</template>

<script>
import plantService from '../services/PlantService.js';

export default {
    data() {
        return {
            plant: {}
        }
    },

    methods: {
        getDefaultImage() {
            return 'https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png';
        },
        onAddClick() {
            this.$store.commit('MODIFY_PLANT_FOR_ADDITION', this.plant);
            this.$store.state.transferPlantJSON.garden_id = this.$store.state.user_garden.garden_id;

            plantService.addPlantToGarden(this.$store.state.transferPlantJSON);
            // this.plant = this.$store.state.userPlants[this.$store.state.userPlants.length - 1];

            alert('Added one ' + this.plant.common_name + ' to your garden!');

            plantService.getGardenByUserId(this.$store.state.user.id)
                .then(response => {
                    this.gardenArray = response.data;
                    this.$store.commit('SET_USER_GARDEN', this.gardenArray[0]);

                    plantService.getPlantsByGarden(this.$store.state.user_garden.garden_id)
                        .then(response => {
                            this.$store.commit('SET_USER_PLANTS', response.data);
                        });
                });

            this.$router.push({ name: 'dbPlantDetail', params: { id: this.$store.state.userPlants[this.$store.state.userPlants.length - 1].plant_id } });
        },
        onGardenClick() {
            this.$router.push({ name: 'gardenView', params: { id: this.$store.state.user_garden.garden_id } });
        }
    },

    created() {
        this.plant = this.$store.state.plants[0];
    }

} 
</script>

<style scoped>
#displayImage {
    width: 80vw;
    height: auto;
}

#plantDetails {
    text-align: center;
}

#addButton {
    padding-bottom: 10px;
}

p {
    font-size: 1.2em;
    text-align: center;
}

#addButton {
    text-align: center;
}

#gardenButton {
    text-align: center;
}

.scientificName {
        color: rgb(102, 102, 102);
    }

@media screen and (min-width: 600px) {
    #wholeView {
        display: flex;

    }

    h2 {
        padding-left: 5px;
    }

    #displayImage {
        width: 32.5vw;
        height: auto;
    }

    #plantDetails {
        text-align: left;
        padding-left: 1vw;
        grid-area: plant;
    }

    #careDetails {
        grid-area: details;

        padding-left: 3vw;
        padding-right: 3vw;
        padding-top: 15vh;
    }

    .scientificName {
        color: rgb(102, 102, 102);
    }
}
</style>