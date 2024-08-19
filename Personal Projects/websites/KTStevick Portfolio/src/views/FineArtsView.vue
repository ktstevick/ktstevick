<template>
    <main>
        <div class="container">
            <div class="box1">
                <CarouselGallery v-bind:gallery="this.$store.state.fine_art" />
            </div>

            <div class="box2">
                <div class="description">
                    <h2>FINE ARTS</h2>
                    <p>
                        &nbsp; This series is called "Imperfect Light" - I first began working on it during my senior year
                        of
                        college, and have continued ever since. These paintings were almost all made in Chicago, and draw
                        from my time in the city.
                    </p>
                    <p>
                        &nbsp; There's a lot I could say about this work, but I'll keep it brief. These city scapes and
                        nocturnes are painted in a pixellated style - that's right, don't wait
                        for them to load! This style, once a necessity due to technical limitations, has long since been
                        left behind. This aesthetic no longer has a pragmatic function, and is essentially
                        only seen in nostalgic or otherwise sentimental settings (indie video games, vaporwave art, et
                        alia). For me though, it served a purpose entirely its own.
                    </p>
                    <p>
                        &nbsp; When I was younger, I was told that the world isn't black and white. While this may be true,
                        it IS red, or blue, or purple, etc... I've always struggled to parse the sensory field
                        and find any meaningful patterns, but this visual style provided me with the tools I needed to look
                        at a single spot, see what color it truly was, and represent that by hand.
                    </p>
                    <p>
                        &nbsp; There's a lot more conceptual weight here than I can really get into, but if you see me and
                        ask I'll never stop talking about it. As I update this site with more
                        of my work in the future, I'll take the time to talk about each individual piece. There's a lot of
                        thinking, working, and often a good story or two that goes into all of them.
                    </p>
                </div>

                <div class="linkOne" v-on:click="toIllustration">
                    <img class="banner" src="../assets/images/banner2.jpg" />
                    <span class="text-context">
                        <h1>Illustration</h1>
                    </span>
                </div>
                <div class="linkTwo" v-on:click="toSoftware">
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
/* Consolidated */
.banner {
    height: 10vh;
    width: 100%;

    object-fit: cover;
}

.description {
    background-color: rgb(255, 236, 240);
    margin-top: 3vw;
    border-radius: 15px;
}

.description h2 {
    text-align: center;
    font-size: 2.5em;
    background-color: rgb(255, 219, 225);

    border-top-left-radius: 15px;
    border-top-right-radius: 15px;
}

.description p {
    font-size: 1.2em;
    margin: 10px;
}

.description p:last-of-type {
    padding-bottom: 6vw;
}

.linkOne {
    height: 10vh;
    margin-bottom: 3vw;
    position: relative;
}

.linkTwo {
    height: 10vh;
    margin-bottom: 3vw;
    position: relative;
}

.text-context {
    background-color: rgba(0, 5, 30, 0.25);
    color: white;
    cursor: pointer;

    height: 10vh;
    width: 100%;

    -webkit-transition: opacity 500ms;
    -moz-transition: opacity 500ms;
    -o-transition: opacity 500ms;
    transition: opacity 500ms;

    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    display: flex;
    text-align: center;
}

.text-context h1 {
    font-size: 7vw;
    letter-spacing: 10px;

    align-self: center;
    margin: 0 auto;
}

/* Small devices (Phones below 600px) */
@media only screen and (max-width: 600px) {}

/* Large phones and tablets (above 600px) */
@media only screen and (min-width: 600px) {
    .description {
        margin: 4vh;
    }

    .description p:last-of-type {
        padding-bottom: 3vw;
    }

    .text-context h1 {
        font-size: 5vw;
    }
}

/* Large tablets and all desktops (above 1180px) */
@media only screen and (min-width: 1180px) {
    .container {
        display: grid;
        grid-template-columns: 1fr 1fr;
    }

    .box1 {
        height: 90vh;
        min-width: 500px;
        min-height: 800px;
    }

    .box2 {
        height: 90vh;
        min-width: 750px;
        min-height: 800px;
        margin-left: 3vw;
        display: grid;
        /* grid-template-rows: 1fr 1fr 4fr; */
        grid-template-areas:
            "linkOne"
            "linkTwo"
            "description";
    }

    .banner {
        height: 10vh;
        min-height: 100px;
        width: 100%;

        object-fit: cover;
        border-radius: 5px;
    }

    /* Originally a single class, split to make grid work easier */
    .linkOne {
        height: 10vh;
        min-height: 100px;

        margin: 4vh 2vh 0 2vh;
        border-radius: 10px;

        grid-area: linkOne;
    }

    .linkTwo {
        height: 10vh;
        min-height: 100px;

        margin: 2vh;
        border-radius: 10px;

        grid-area: linkTwo;
    }

    .description {
        border-radius: 15px;

        grid-area: description;
    }

    .description h2 {
        text-align: center;
        font-size: 2em;

        border-top-left-radius: 15px;
        border-top-right-radius: 15px;
    }

    .description p {
        font-size: 1.2em;
        margin: 20px 30px;
    }

    .description p:last-of-type {
        padding-bottom: 0;
    }


    .text-context {
        background-color: rgba(0, 5, 30, 0.5);
        opacity: 0;

        min-height: 100px;
        border-radius: 5px;
    }

    .text-context h1 {
        font-size: 1.5vw;
        letter-spacing: 10px;
    }

    span.text-context:hover {
        opacity: 1;
    }
}
</style>
