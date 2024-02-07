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
                        actually wanted to be a draftsman originally - I'll save the story of how I wound up a painter for another time.
                    </p>
                    <p>
                        &nbsp; Two midtone drawings - one with pencil on gray paper, the other with charcoal on white paper I actually toned myself.
                        I had half a dozen pastel drawings over my stay but this remains one of my favorite. Transitioning with color to two cell shaded marker pieces,
                        just so you can get an idea of how I like to organize my marks and construct my outlines.
                    </p>
                    <p>
                        &nbsp; In the art world, drawing is often viewed as something one does BEFORE they make art, a painting, a performance, any piece. I don't think it stems
                        from a place of hate, but rather, that drawing is overlooked in this way for the same reason we have a hard time seeing our nose. I wouldn't go as far as to say
                        any of these images represent fine art, but I always have and always will love drawing and absolutely revel with delight when I happen upon it in a gallery.
                    </p>
                    <p>
                        &nbsp; It's a very brave way of saying "This exists, and it IS art. No sense overworking it."
                    </p>
                    <p>
                        &nbsp; In the future, I'll be able to illustrate for you more about my working methods with drawings. For now, enjoy the sneak peek into my process!
                    </p>
                </div>

                <div class="fineArtsLink" v-on:click="toFineArts">
                    <img class="banner" src="../assets/images/banner1.jpg" />
                    <span class="text-context">
                        <h1>Fine Arts</h1>
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
    /* background-color: hsl(241, 100%, 75%); */
    height: 75vh;
    margin-top: 10px;
    grid-area: content;
}

.box2 {
    /* background-color: hsl(241, 100%, 75%); */
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

    width: 208px;

    font-size: 2em;
    text-align: center;
}

.description p {
    margin: 1vh;
    padding-top: .5vh;

    font-size: 1.05em;
}

.fineArtsLink {
    height: 12vh;
    width: 12vw;

    margin-top: 1vh;
    margin-left: 1vw;
    margin-right: 1vw;

    /* background-color: hsl(241, 62%, 63%); */
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

    /* background-color: hsl(241, 62%, 63%); */
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
}</style>
