<!-- I'll have to circle back to this design... home page has to be a little punchier and a little more polished, I'd say -->

<template>
  <main>
    <div class="container">
      <div class="box1">
        <h2>Work-in-Progress:</h2>
        <p>
          &nbsp; This is an oil painting that will be the latest piece in the Imperfect Light series.
          I'm painting it on a found object that strongly resembles a traditional left turn only traffic sign. It's one
          of a few found object pieces I've worked on and I'm thrilled to be getting more experience with them! I have half a mind to make them a series of their own... but I'm 
          using this piece as a learning opportunity. (You don't always get that choice!)
        </p>
      </div>

      <CarouselBase class="box2" v-bind:gallery="this.$store.state.in_progress" />

      <!-- This is going to NEED a better image -->
      <div class="box3" v-on:click="toPortfolio">
        <img class="banner" src="../assets/images/banner4.jpg"/>
        <span class="text-context">
          <h1>P o r t f o l i o</h1>
        </span>
      </div>

      <CarouselBase class="box4" v-bind:gallery="this.$store.state.just_finished" />

      <div class="box5">
        <h2>Recently Finished:</h2>
        <p>
          &nbsp; This was a lighthearted little acrylic piece I made last week just for fun. Though I initially believed it to be
          a squirrel,
          this animal may in fact be some kind of fox. The sprite itself is from Harvest Moon on the SNES. I've been
          studying motion extensively
          this last year and this was a fun test to see how illusion and animation play with one another - or rather, how
          they play when separated.
        </p>
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
      this.$router.push({ name: 'portfolio'});
    },
  }
}
</script>

<style scoped>
.container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 20vh;
  grid-template-areas: 
    "box1 box2"
    "box3 box3"
    "box4 box5";

  gap: 6vh;
}

.box1 {
  grid-area: box1;
}

.box2 {
  grid-area: box2;
}

.box3 { /* THIS ONE controls the middle box */
  height: 20vh;

  grid-area: box3;
  position: relative;
}

.banner {
  height: 20vh;
  width: 100%;
  
  object-fit: cover;

  border-radius: 10px;
}

.box4 {
  grid-area: box4;
}

.box5 {
  grid-area: box5;
}

h2 {
  margin: 1vh;
  border-bottom: 2px solid black;

  width: 270px;

  font-size: 3.2vh;
  text-align: center;
}

p {
  margin: 1vh;
  padding-top: .5vh;

  font-size: 1.5vh;
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

  height: 20vh;
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

  border-radius: 10px;
}

.text-context h1 {
  font-size: 6vh;

  padding-top: 6vh;
  padding-bottom: 6vh;
}

span.text-context:hover  {
  opacity: 1;
}

</style>