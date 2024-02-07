<!-- This one is a little different from the others, since programs don't necessarily have or NEED images -->
<!-- I'd like to havea  brief description of each project IN my GitHub, as well as a video demo of Plants and GemQuest at some point -->
<!-- In addition, this page will include the JavaScript demos for fun. Right now I just want to connect to a 3rd party API. -->

<!-- So, what do I need to make this happen? -->

<!-- Let's start with the obvious - what projects ARE in your GitHub? It makes sense to break things into categories, I think. -->

<!-- PERSONAL PROJECTS -->
    <!-- PKMN Damage Calculator -->
    <!-- Assessment Reflections -->
    <!-- GEMQUEST --> <!-- VIDEO -->

<!-- TE CAPSTONES -->
    <!--First Capstone -->
    <!-- Second Capstone -->
    <!-- Third Capstone --> <!-- VIDEO -->

<!-- WEB DEVELOPMENT -->
    <!-- This site, obviously -->
    <!-- JavaScript Apps -->

<!-- Which, honestly? ISN'T that much to talk about. -->

<!-- The REAL challenge for this page is that everything else fits neatly into a single page. How could I arrange this so we never had to scroll? -->

<!-- I have a few really cool ideas for this - chief among them is the Manilla Folder approach, with tabs for each section and that whole part of the grid dedicated to the description... -->
<!-- But I think for now I'm going to keep it pretty bare bones. Fix the links, write a good intro, and then encourage viewers to go to the GitHub themselves. -->

<template>
  <main>
    <div class="container">
      <div class="box1">
        <h2>SOFTWARE DEVELOPMENT</h2>
        <p>
          &nbsp; This page is a bit different from the others. My team projects, Tech Elevator capstones, and personal projects are all available for review
          on my GitHub page - the link for which can be found on the right, but also below the title card on the left. These are just a few quick notes to pique
          your interest, get your attention, or otherwise let you know these projects might be worth a closer look.
        </p>
        <p>
          &nbsp; Still not 100% sure how I want to format this page, but for now I think just text should suffice. Maybe an image or two. I'll play around with it a bit - that's 
          how I like to develop best anyways.
        </p>
      </div>

      <!-- Links can all be found here -->
      <div class="box2">

        <div class="link" v-on:click="toGitHub">
          <span class="text-context">
            <h1>GitHub</h1>
          </span>
        </div>

        <div class="link" v-on:click="toFineArts">
          <img class="banner" src="../assets/images/banner1.jpg" />
          <span class="text-context">
            <h1>Fine Arts</h1>
          </span>
        </div>

        <div class="link" v-on:click="toIllustration">
          <img class="banner" src="../assets/images/banner2.jpg" />
          <span class="text-context">
            <h1>Illustration</h1>
          </span>
        </div>

        <div class="link" v-on:click="dadJoke">
          <span class="text-context">
            <h1>Dad Jokes</h1>
          </span>
        </div>
      </div>

      <!-- It might be worth making these link straight to the projects, but I don't want to oversaturate the page with redirection - OR reflection. Finding the right balance between saying too much and too little will take time. -->
      <div class="box3">
        <h2>TE Capstones</h2>

        <hr>
        <h3>The Green Thumb</h3>
        <p>
            &nbsp; Test 1 2
        </p>

        <hr>
        <h3>TEnmo Bank App</h3>
        <p>
          &nbsp; Test 1 2
        </p>

        <hr>
        <h3>Vending Machine</h3>
        <p>
          &nbsp; Test 1 2
        </p>
      </div>

      <div class="box4">
        <h2>Personal Projects</h2>
        <hr>

        <h3>PKMN Damage Calculator</h3>
        <p>
          &nbsp; This application is a demonstration of a very popular game's damage engine, reverse engineered by myself and coded entirely from scratch. This is actually 
          my first personal project and I still like to improve it from time to time. I had to manually truncate certain data to properly simulate the hardware on a Gameboy Color in order to return accurate results.
        </p>

        <hr>
        <h3>Web Development</h3>
        <p>
          &nbsp; This site is my primary piece - I'm rather proud of it. This site was built with Vue3, incorporates a Vue Carouself from THIS PAGE, and 
          utilizes axios for 3rd party API calls, of which there is only one at the moment. Click on the purple box to receive a random "Dad Joke" from the public and free 
          icanhazdadjoke API.
        </p>

        <hr>
        <h3>GemQuest</h3>
        <p>
          &nbsp; Currently in development by Ian Bird and myself.
        </p>
      </div>
    </div>
  </main>
</template>
  
<script>
import jokeService from '../services/jokeService.js'

export default {
  data() {
    return {
      joke: ''
    }
  },

  // Runs once on created to buffer a joke 
  created() {
    jokeService.getRandomJoke()
      .then((response) => {
        this.joke = response.data;
      })
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
    },
    toGitHub() {
      window.location.href = 'https://github.com/ktstevick/ktstevick';
    },
    dadJoke() {
      alert(this.joke);

      // API call to set new joke after displaying current one
      jokeService.getRandomJoke()
        .then((response) => {
          this.joke = response.data;
        })
    }
  }
}
</script>
  
<style scoped>
.container {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr;
  grid-template-areas:
    "intro intro link"
    "capstone personal link"
    "capstone personal link";
  gap: 6vh;
}

.box1 {
  background-color: hsl(0, 0%, 90%);
  height: 20vh;
  width: auto;
  margin-top: 2vh;
  grid-area: intro;
}

.box2 {
  /* background-color: hsl(275, 13%, 51%); */

  height: 60vh;
  width: 12vw;

  margin-left: .25vw;
  margin-top: 1.9vh;

  grid-area: link;

  display: grid;
}

.link {
  height: 12vh;
  position: relative;

  background-color: rgba(119, 0, 255, 0.753);
  border-radius: 5px;
}

.box3 {
  background-color: hsl(0, 0%, 70%);
  height: 47vh;
  margin-top: 10px;
  grid-area: capstone;
}

.box4 {
  background-color: hsl(0, 0%, 60%);
  height: 47vh;
  margin-top: 10px;
  grid-area: personal;
}

.box5 {
  background-color: hsl(0, 0%, 50%);
  height: 47vh;
  margin-top: 10px;
  grid-area: jsApps;
}

main {
  /* background-color: pink; */
  margin-left: 3vw;
  margin-top: auto;
  margin-bottom: auto;
}

.banner {
  height: 12vh;
  width: 12vw;

  object-fit: cover;
  border-radius: 5px;
}

.text-context {
  background-color: rgba(0, 5, 30, 0.5);
  color: white;
  cursor: pointer;

  height: 12vh;
  width: 12vw;

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
  