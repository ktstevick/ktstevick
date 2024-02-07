<!-- THIS is the money maker. I'm thinking just the one series for right now, but this will be VERY developed moving forwards -->

<template>
    <main>
        <div class="container">
            <div class="box1">
                <CarouselGallery v-bind:gallery="this.$store.state.fine_art" />
            </div>

            <!-- It would be cool to v-bind pieces and have the description change, but for now it's just an overall view of the series -->
            <div class="box2">
                <div class="description">
                    <h2>FINE ARTS</h2>
                    <p>
                        &nbsp; This series is called "Imperfect Light" - I first began working on it during my senior year of
                        college, and have continued ever since. These paintings were almost all made in Chicago, and draw from my time in the city.
                    </p>
                    <p>
                        &nbsp; There's a lot I could say about this work, but I'll keep it brief. These city scapes and nocturnes are painted in a pixellated style - that's right, don't wait 
                        for them to load! This style, once a necessity due to technical limitations, has long since been left behind. This aesthetic no longer has a pragmatic function, and is essentially
                        only seen in nostalgic or otherwise sentimental settings (indie video games, vaporwave art, et alia). For me though, it served a purpose entirely its own.
                    </p>
                    <p>
                        &nbsp; When I was younger, I was told that the world isn't black and white. While this may be true, it IS red, or blue, or purple, etc... I've always struggled to parse the sensory field 
                        and find any meaningful patterns, but this visual style provided me with the tools I needed to look at a single spot, see what color it truly was, and represent that by hand.
                    </p>
                    <p>
                        &nbsp; There's a lot more conceptual weight here than I can really get into, but if you see me and ask I'll never stop talking about it. As I update this site with more
                        of my work in the future, I'll take the time to talk about each individual piece. There's a lot of thinking, working, and often a good story or two that goes into all of them.
                    </p>
                </div>

                <div class="designLink" v-on:click="toIllustration">
                    <img class="banner" src="../assets/images/banner2.jpg" />
                    <span class="text-context">
                        <h1>Illustration</h1>
                    </span>
                </div>
                <div class="softwareLink" v-on:click="toSoftware">
                    <img class="banner" src="../assets/images/banner3.jpg" />
                    <span class="text-context">
                        <h1>Software</h1>
                    </span>
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
        toIllustration() {
            this.$router.push({ name: 'illustration' });
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

.banner {
    height: 12vh;
    width: 100%;
    object-fit: cover;
    border-radius: 5px;
}

.box1 {
    /* background-color: hsl(108, 100%, 75%); */
    height: 75vh;
    margin-top: 10px;
    grid-area: content;
}

.box2 {
    /* background-color: hsl(108, 100%, 75%);*/
    /* background-color: hsl(0, 0%, 80%); */

    height: 75vh;
    margin-top: 1vh;
    grid-area: description;

    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas:
        "linkOne linkTwo"
        "description description";
}

.description {
    background-color: rgba(255, 221, 184, 0.5);

    height: 46vh;

    margin-left: 1vw;
    margin-right: 5vw;

    grid-area: description;
}

.description h2 {
    margin: 1vh;
    border-bottom: 2px solid black;

    width: 150px;

    font-size: 2em;
    text-align: center;
}

.description p {
    margin: 1vh;
    padding-top: .5vh;

    font-size: 1.05em;
}

.designLink {
    height: 12vh;
    width: 12vw;

    margin-top: 1vh;
    margin-left: 1vw;
    margin-right: 1vw;
    border-radius: 10px;

    /* background-color: hsl(108, 40%, 52%); */
    grid-area: linkOne;

    position: relative;
}

.softwareLink {
    height: 12vh;
    width: 12vw;

    margin-top: 1vh;
    margin-left: 1vw;
    margin-right: 1vw;
    border-radius: 10px;

    /* background-color: hsl(108, 40%, 45%); */
    grid-area: linkTwo;

    position: relative;
}

main {
    /* background-color: pink; */
    margin-left: 3vw;
    margin-top: auto;
    margin-bottom: auto;
}

.text-context {
    background-color: rgba(0, 5, 30, 0.5);
    color: white;
    cursor: pointer;

    height: 12vh;
    width: 100%;

    opacity: 0;
    -webkit-transition: opacity 500ms;
    -moz-transition: opacity 500ms;
    -o-transition: opacity 500ms;
    transition: opacity 500ms;

    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    display: table;
    text-align: center;

    border-radius: 5px;
}

.text-context h1 {
    margin-top: 4vh;
}

span.text-context:hover {
    opacity: 1;
}
</style>
