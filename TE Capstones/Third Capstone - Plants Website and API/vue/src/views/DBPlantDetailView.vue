<template>
    <data-base-plant-detail v-bind:plant="plant" />

</template>

<script>
    import DataBasePlantDetail from "../components/DataBasePlantDetail.vue";
    import plantService from "../services/PlantService.js";

    export default { 
        data() {
            return { 
                plant: {}
            }
        },
        components: {
            DataBasePlantDetail,
        },
        methods: {
            getPlantById() {
                plantService.getPlantById(this.plant.plant_id).then( (response) => {
                    this.plant = response.data;
                })
            }
            // getPlantFromSTore() {
            //     this.plant = this.$store.state.plants[0];
            // }
        }
    };
</script>

<style scoped>

</style>