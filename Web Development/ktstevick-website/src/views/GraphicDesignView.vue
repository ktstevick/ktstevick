<!-- Graphic Design page. Not a personal favorite, but still an important one to include for UI/UX type positions -->

<template>
    <main>
        <div class="container">
            <div class="box1">
                <CarouselGallery />
            </div>

            <!-- It would be cool to v-bind pieces and have the description change, but for now it's just an overall view of the series -->
            <div class="box2">
                <div class="description">
                    DESCRIPTION
                </div>
                <div class="fineArtsLink" v-on:click="toFineArts">
                    FINE ARTS
                </div>
                <div class="softwareLink" v-on:click="toSoftware">
                    SOFTWARES
                </div>
            </div>
        </div>
    </main>
</template>

<script>
import CarouselGallery from '../components/CarouselGallery.vue';

export default {
    components: {
        CarouselGallery
    },

    methods: {
        toFineArts() {
            this.$router.push({ name: 'fineArts' });
        },
        toGraphicDesign() {
            this.$router.push({ name: 'graphicDesign' });
        },
        toSoftware() {
            this.$router.push({ name: 'software' });
        }
    }
}
</script>

<style scoped>
.container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas:
        "content description"
        "content description"
        "content description";
    gap: 6vh;
}

.box1 {
    background-color: hsl(241, 100%, 75%);
    height: 75vh;
    margin-top: 10px;
    grid-area: content;
}

.box2 {
    background-color: hsl(241, 100%, 75%);
    height: 75vh;
    margin-top: 10px;
    grid-area: description;
    
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas: 
        "description description"
        "linkOne linkTwo";
}

.description {
    margin-top: 5vh;
    height: 47vh;
    background-color: hsl(241, 62%, 63%);
    grid-area: description;
}

.fineArtsLink {
    margin-bottom: 7vh;
    margin-left: 1vw;
    margin-right: 1vw;
    border-radius: 10px;

    background-color: hsl(241, 62%, 63%);
    grid-area: linkOne;
}

.softwareLink {
    margin-bottom: 7vh;
    margin-left: 1vw;
    margin-right: 1vw;
    border-radius: 10px;

    background-color: hsl(241, 62%, 63%);
    grid-area: linkTwo;
}

main {
    /* background-color: pink; */
    margin-left: 3vw;
    margin-top: auto;
    margin-bottom: auto;
}
</style>
