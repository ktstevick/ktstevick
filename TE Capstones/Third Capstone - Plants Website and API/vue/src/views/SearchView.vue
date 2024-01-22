<template>
    <div id="searchView">
        <h2>Plant Search</h2>

        <form class="searchForm" v-on:submit.prevent="searchPlants(this.searchString)">
            <input class="searchBar" type="text" v-model="searchString" />
            <input type="submit" class="searchButton" value="search"/>
        </form>

        <div v-if="isLoading" class="loadingImage">
            <img src="../assets/loading_animation.gif" id="loadingImage" />
        </div>

        <div v-else class="searchResults" v-for="plant in plants" v-bind:key="plant.id">
            <plant-card v-bind:plant="plant" />
        </div>
    </div>
</template>

<!-- Some kind of loading screen may be necessary, 3 to 6 second delay -->
<script>
import PlantCard from '../components/PlantCard.vue';
import plantService from '../services/PlantService.js';

export default {
    data() {
        return {
            searchString: '',
            plants: [],
            isLoading: false
        };
    },
    components: {
        PlantCard
    },
    methods: {
        searchPlants(searchString) {
            this.isLoading = true;

            if (searchString == '') {
                alert("You're not searching for anything!");
                this.isLoading = false;
            } else {

                plantService.getPlants(searchString)
                    .then(response => {
                        this.plants = response.data;
                        this.isLoading = false;
                    })
            }
        },
    }
};
</script>

<style scoped>
h2 {
    text-align: center;
}

.searchResults{
    display: flex;
    justify-content: center;
    width: 80vw;
    
}

.searchForm {
    text-align: center;
}

.loadingImage {
    text-align: center;
}

#loadingImage {
    height: 50vh;
    width: auto;
}

@media screen and (min-width: 600px) {
    .searchResults {
        display: inline-flex;
        width: 32vw;
    }
}

@media screen and (min-width: 992px) {
    .searchResults {
        display: inline-flex;
        width: 19vw;

        padding-top: 5vh;
    }

    #searchView {
        width: 80vw;
    }
}

</style>