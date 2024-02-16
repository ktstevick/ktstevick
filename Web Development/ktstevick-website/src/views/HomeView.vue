<!-- Idle Thoughts: In tablet/mobile view, some styled borders around the banner to make the visual shift slightly less abrasive (gradient)-->

<template>
  <main>
    <div class="container">
      <div id="descriptionCard1">
        <h2> &nbsp; Work-in-Progress:</h2>
        <div class="description">
          <p>
            &nbsp; This is an oil painting that will be the latest piece in the Imperfect Light series.
            I'm painting it on a found object that strongly resembles a traditional left turn only traffic sign. It's one
            of a few found object pieces I've worked on and I'm thrilled to be getting more experience with them! I have
            half a mind to make them a series of their own... but I'm
            using this piece as a learning opportunity.
          </p>
        </div>
      </div>

      <CarouselBase id="carousel1" v-bind:gallery="this.$store.state.in_progress" />

      <!-- This is going to NEED a better image -->
      <div id="bannerBox" v-on:click="toPortfolio">
        <img class="banner" src="../assets/images/banner4.jpg" />
        <span class="text-context">
          <h1>View Portfolio</h1>
        </span>
      </div>

      <CarouselBase id="carousel2" v-bind:gallery="this.$store.state.just_finished" />

      <div id="descriptionCard2">
        <h2>&nbsp; Recently Finished:</h2>
        <div class="description">
          <p>
            &nbsp; This was a lighthearted little acrylic piece I made last week just for fun. Though I initially believed
            it to be
            a squirrel,
            this animal may in fact be some kind of fox. The sprite itself is from Harvest Moon on the SNES. I've been
            studying motion extensively
            this last year and this was a fun test to see how illusion and animation play with one another - or rather,
            how
            they play when separated.
          </p>
        </div>
      </div>

    </div>
  </main>
</template>

<script>
import CarouselBase from '../components/CarouselBase.vue'

export default {
  components: {
    CarouselBase
  },

  methods: {
    toPortfolio() {
      this.$router.push({ name: 'portfolio' });
    },
  }
}
</script>

<style scoped>
/* Consolidated */
#descriptionCard1 {
  background-color: rgb(238, 236, 240);

  margin: 3vh 3vh 4.5vh 3vh;
  border-radius: 15px;
}

#descriptionCard2 {
  background-color: rgb(238, 236, 240);

  margin: 4.5vh 3vh 3vh 3vh;
  border-radius: 15px;
}

.banner {
  height: 30vh;
  width: 100%;

  object-fit: cover;
}

#bannerBox {
  position: relative;

  height: 30vh;

  margin: 5vh 0;

  text-align: center;
}

h2 {
  font-size: 2em;
  background-color: rgb(208, 206, 210);

  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
}

p {
  font-size: 1.3em;
  padding: 20px;
}

.text-context {
  background-color: rgba(0, 5, 30, 0.5);
  color: white;
  cursor: pointer;

  width: 100%;

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
}

.text-context h1 {
  font-size: 5vh;
  letter-spacing: 1vw;

  padding-top: 11.5vh;
  padding-bottom: 12vh;
}

/* Small devices (Phones below 600px) */
@media only screen and (max-width: 600px) {

}

/* Large phones and tablets (above 600px) */
@media only screen and (min-width: 600px) {
  .banner {
    height: 20vh;
  }

  #bannerBox {
    height: 20vh;
  }

  .text-context h1 {
    font-size: 6vh;
    letter-spacing: 1vw;

    padding-top: 6vh;
    padding-bottom: 6vh;
  }
}

/* Large tablets and all desktops (above 1180px) */
@media only screen and (min-width: 1180px) {
  .container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas:
      "card1 carousel1"
      "banner banner"
      "carousel2 card2";
  }

  #descriptionCard1 {
    height: 30vh;

    margin: 0;
    border-top-left-radius: 20px;
    border-bottom-left-radius: 20px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;

    grid-area: card1;
  }

  #descriptionCard1 h2 {
    border-top-left-radius: 20px;
    border-top-right-radius: 0;
    font-size: 2vw;
  }

  .description {
    line-height: 24vh;
    height: 26vh;
  }

  .description p {
    margin: 0 1.2vw;
    padding: 0;

    display: inline-block;
    vertical-align: middle;

    line-height: 3.25vh;
    font-size: 1vw;
  }

  #carousel1 {
    height: 30vh;
    grid-area: carousel1;
  }

  #bannerBox {
    grid-area: banner;
  }

  .banner {
    object-fit: cover;
    border-radius: 10px;
  }

  #carousel2 {
    height: 30vh;
    grid-area: carousel2;
  }

  #descriptionCard2 {
    background-color: rgb(238, 236, 240);

    height: 30vh;
    width: 34vw;

    margin: 0;
    border-top-right-radius: 20px;
    border-bottom-right-radius: 20px;
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;

    grid-area: card2;
  }

  #descriptionCard2 h2 {
    border-top-left-radius: 0;
    border-top-right-radius: 20px;
    font-size: 2vw;
  }

  .text-context {
    border-radius: 10px;
    opacity: 0;
  }

  span.text-context:hover {
    opacity: 1;
  }
}
</style>