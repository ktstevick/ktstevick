<template>
    <div class="forumDisplay">
        <div class="topTextDiv">
            <h2 class="title">Market Place</h2>
            <h3 class="description">Buy, sell, trade, or chat with friends in our Market.</h3>
        </div>
        <img class="cardImage" src="../assets/garden.jpg" v-on:click="marketForum" />
        <forum-card class="postCard" v-bind:post="market" />
    </div>

    <div class="forumDisplay">
        <div class="topTextDiv">
            <h2 class="title">Seasonal Plants</h2>
            <h3 class="description">Find hot Winter picks and show off your seasonal favorites!</h3>
        </div>
        <img class="cardImage" src="../assets/garden.jpg" v-on:click="seasonalForum" />
        <forum-card class="postCard" v-bind:post="seasonal" />
    </div>

    <div class="forumDisplay">
        <div class="topTextDiv">
            <h2 class="title">Plant Healthcare</h2>
            <h3 class="description">Dedicated disease discussion and diagnostic assistance.</h3>
        </div>
        <img class="cardImage" src="../assets/garden.jpg" v-on:click="diseaseForum" />
        <forum-card class="postCard" v-bind:post="disease" />
    </div>
</template>

<script>
import ForumCard from '../components/ForumCard.vue';
import ForumService from '../services/ForumService';
import PlantService from '../services/PlantService';

export default {
    data() {
        return {
            market: {},
            seasonal: {},
            disease: {},
            plant: this.$store.state.active_plant
        }
    },

    components: {
        ForumCard
    },
    created() {
        ForumService.getPostByForums(1)
            .then(response => {
                let lastIndex = response.data.length - 1;
                this.$store.state.marketplacePreview = response.data[lastIndex];

            });
        ForumService.getPostByForums(2)
            .then(response => {
                let lastIndex = response.data.length - 1;
                this.$store.state.seasonalPreview = response.data[lastIndex];
            });
        ForumService.getPostByForums(3)
            .then(response => {
                let lastIndex = response.data.length - 1;
                this.$store.state.diseasePreview = response.data[lastIndex];
                this.market = this.$store.state.marketplacePreview;
                this.seasonal = this.$store.state.seasonalPreview;
                this.disease = this.$store.state.diseasePreview;
            });

    },
    methods: {
        marketForum() {
            this.$router.push({ name: 'forumById', params: { id: 1 } })
        },
        seasonalForum() {
            this.$router.push({ name: 'forumById', params: { id: 2 } })
        },
        diseaseForum() {
            this.$router.push({ name: 'forumById', params: { id: 3 } })
        }
    }
}
</script>

<style scoped>
.forumDisplay {
    display: flex;
    text-align: center;
    width: 90vw;
    justify-content: center;
    flex-wrap: wrap;

    border-bottom: 2px solid darkgray;
}

.cardImage {
    width: 98vw;

    border-top: 2px solid rgb(23, 63, 22);
    border-right: 2px solid rgb(23, 63, 22);
    border-left: 2px solid rgb(72, 128, 71);
    border-bottom: 2px solid rgb(72, 128, 71);
    border-radius: 4px;
}

.description {
    color: rgb(96, 112, 112);
}

/* .marketPlace{
    background-color: hotpink;
} */

@media screen and (min-width: 600px) {
    .forumDisplay {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-template-areas:
            "title desc"
            "image post";

        text-align: left;

        width: 80vw;

        padding-left: 1vw;
        padding-bottom: 3vh;

        border-bottom: 2px solid darkgray;
    }

    .topTextDiv {
        display: inline-flex;
    }

    .title {
        grid-area: title;
        padding-left: 5px;
    }

    .description {
        grid-area: desc;
        font-size: 1em;

        padding-top: 1vh;
        padding-left: 1vw;

        color: rgb(96, 112, 112);
    }

    .postCard {
        grid-area: post;
        margin-left: 2vw;
    }

    .cardImage {
        grid-area: image;
        width: 50vw;
    }

}
</style>