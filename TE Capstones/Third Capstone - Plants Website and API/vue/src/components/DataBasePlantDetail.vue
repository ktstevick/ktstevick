<template>
    <div id="wholeView">
        <div id="plantDetails">
            <h2>{{ this.plant.common_name }}</h2>
            <h2 class="scientificName">{{ this.plant.scientific_name }}</h2>

            <img v-if="plant.plant_img === null && plant.regular_img_url === null" v-bind:src="getDefaultImage"
                class="displayImage" />
            <img v-else-if="plant.plant_img != null" v-bind:src="plant.plant_img" class="displayImage" />
            <img v-else v-bind:src="plant.regular_img_url" class="displayImage" />
        </div>

        <div id="careDetails">
            <p>{{ plant.plant_description }}</p>

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

            this.plant.garden_id = this.$store.state.user_garden.garden_id;

            plantService.addPlantToGarden(this.plant);

            alert('Added one ' + this.plant.common_name + ' to your garden!');
        },
        onGardenClick() {
            this.$router.push({ name: 'gardenView', params: { id: this.$store.state.user_garden.garden_id } });
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

                this.plant = this.$store.state.plants[0];
            })
    }
} 
</script>

<style scoped>
.displayImage {
    width: 80vw;
    height: auto;
}

#plantDetails {
    text-align: center;
}

#addButton {
    padding-bottom: 10px;
    text-align: center;
}

p {
    font-size: 1.2em;
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

    .displayImage {
        width: 32.5vw;
        height: auto;
    }

    #plantDetails {
        grid-area: plant;
        text-align: left;
        padding-left: 1vw;
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