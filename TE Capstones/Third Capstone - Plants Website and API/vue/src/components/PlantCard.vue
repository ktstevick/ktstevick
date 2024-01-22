<template>
    <div class="card">
        <div class="cardTop">
            <h2>{{ plant.common_name }}</h2>
        </div>

        <img v-if="plant.default_image === null" v-bind:src="getDefaultImage" class="cardImage"/>
        <img v-else-if="plant.plant_img === null" v-bind:src="getDefaultImage" class="cardImage"/>
        <img v-else v-bind:src="plant.default_image.original_url"  class="cardImage" v-on:click="onDetailsClick"/>
        
        <div class="cardBottom">
            <button v-on:click="onAddClick" class="cardButton">ADD TO GARDEN</button>
            <button v-on:click="onDetailsClick" class="cardButton">DETAILS</button>
        </div>
    </div>
</template>

<script>
    import plantService from '../services/PlantService.js';

    export default {
        props: {
            plant: { type: Object, required: true }
        },

        methods: {
            onDetailsClick() {
                this.$router.push({ name: 'detailById', params: {id: this.plant.id}});
                this.$store.commit('STORE_PLANT', this.plant); 
            },
            onAddClick() {
                // We have to assign the garden Id TO the plant before we pass it
            
                this.$store.commit('MODIFY_PLANT_FOR_ADDITION', this.plant);
                this.$store.state.transferPlantJSON.garden_id = this.$store.state.user_garden.garden_id;

                plantService.addPlantToGarden(this.$store.state.transferPlantJSON);

                alert('Added one ' + this.plant.common_name + ' to your garden!');
            },
            onDeleteClick() {
                this.$store.commit('DELETE_PLANT_FROM_GARDEN', this.plant);
                alert('Deleted this ' + this.plant.common_name + ' from your garden.');
            }

        },

        computed: {
            getDefaultImage() {
                return 'https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png';
            }
        }
    }
</script>

<!-- We can fix image ratio by specifying width and leaving height undefined -->
<style scoped>
.card {
  text-align: center;
  border: 2px solid black;
  background-color: rgb(212, 255, 212);
  border-radius: 10px;
  width: 80vw;
  height: auto;
  margin: 20px;
  padding: 10px;
}

.cardImage {
  margin-top: 5px;
  width: 78vw;
  height: auto;
}

.cardTop {
    height: auto;
}

.cardButton {
    font-size: 1.2em;
    font-weight: bold;
    width: 40vw;
    height: 60px;

}

.cardBottom {
    display: flex;
    }

@media screen and (min-width: 600px) {
    .card {
        width: 33vw;
        margin: 2px;
        height: auto;
        background-color: rgb(212, 255, 212);
    }
    .cardTop {
        height: auto;
    }
    .cardImage {
        height: auto;
        width: 28vw;
    }
    .cardButton {
        width: 15vw;
    }

}

@media screen and (min-width: 992px) {
    
    .card {
        width: 16vw;
        margin: 10px;

        height: auto;
        background-color: rgb(228, 255, 228);
    }
    .cardTop {
        height: auto;
    }

    .cardImage {
        height: auto;
        width: 15vw;
    }

    .cardButton {
        width: 7.5vw;
    }

    .cardBottom {
        padding-top: 5px;

        display: flex;
        justify-content: center;
    }
}
</style>