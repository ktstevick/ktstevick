<!-- Graphic Design page. Not a personal favorite, but still an important one to include for UI/UX type positions -->

<template>
    <main>
        <div class="container">
            <div class="box1">
                <CarouselGallery v-bind:gallery="this.$store.state.illustration" />
            </div>

            <!-- It would be cool to v-bind pieces and have the description change, but for now it's just an overall view of the series -->
            <div class="box2">
                <div class="description">
                    <h2>ILLUSTRATION</h2>
                    <p>
                        &nbsp; This gallery is just a sample of illustrations from my time at the American Academy of Art. I
                        actually wanted to be a draftsman originally - I'll save the story of how I wound up a painter for
                        another time.
                    </p>
                    <p>
                        &nbsp; Two midtone drawings - one with pencil on gray paper, the other with charcoal on white paper
                        I actually toned myself.
                        I had half a dozen pastel drawings over my stay but this remains one of my favorite. Transitioning
                        with color to two cell shaded marker pieces,
                        just so you can get an idea of how I like to organize my marks and construct my outlines.
                    </p>
                    <p>
                        &nbsp; In the art world, drawing is often viewed as something one does BEFORE they make art, a
                        painting, a performance, any piece. I don't think it stems
                        from a place of hate, but rather, that drawing is overlooked in this way for the same reason we have
                        a hard time seeing our nose. I wouldn't go as far as to say
                        any of these images represent fine art, but I always have and always will love drawing and
                        absolutely revel with delight when I happen upon it in a gallery.
                    </p>
                    <p>
                        &nbsp; It's a very brave way of saying "This exists, and it IS art. No sense overworking it."
                    </p>
                    <p>
                        &nbsp; In the future, I'll be able to illustrate for you more about my working methods with
                        drawings. For now, enjoy the sneak peek into my process!
                    </p>
                </div>

                <div class="linkOne" v-on:click="toFineArts">
                    <img class="banner" src="../assets/images/banner1.jpg" />
                    <span class="text-context">
                        <h1>Fine Arts</h1>
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
    background-color: rgb(255, 239, 228);
    margin-top: 3vw;
    border-radius: 15px;
}

.description h2 {
    text-align: center;
    font-size: 2.5em;
    background-color: rgb(236, 211, 194);

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