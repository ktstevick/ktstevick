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
        <h1>SOFTWARE DEVELOPMENT</h1>
        <p>
          &nbsp; This page is a bit different from the others. My team projects, Tech Elevator capstones, and personal
          projects are all available for review
          on my GitHub page - the link for which can be found on the right, but also below the title card on the left.
          These are just a few quick notes to pique
          your interest, get your attention, or otherwise let you know these projects might be worth a closer look.
        </p>
        <p>
          &nbsp; Still not 100% sure how I want to format this page, but for now I think just text should suffice. Maybe
          an image or two. I'll play around with it a bit - that's
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

        <h3>The Green Thumb</h3>
        <p>
          &nbsp; This was our third and final capstone. Our assignment was to make a forum based website where users could
          create custom and community gardens, buy and sell plants, seek help for diseased plants, and message each other
          directly. We worked closely with a Product Owner and Scrum Master on a team of four developers. I primarily
          focused on the styling, front-end, API, and ERDs - larger architectural concepts for the project, as well as
          specific visual details and presentation. The color scheme for
          the site was something I had to argue with my team to keep, but the Product Owner was beyond impressed with our
          final result. Overall very proud of this project.
        </p>

        <h3>TEnmo Bank App</h3>
        <p>
          &nbsp; For our second capstone, we essentially recreated Venmo as a CLI application. We had to code this API
          from scratch - the app allowed users to register and create accounts, login, manage balances, send and receive
          money,
          as well as request money and check the details of individual transactions at will. This was my first big API
          project and it was honestly a lot of fun.
        </p>

        <h3>Umbrella LLC Vending Machine</h3>
        <p>
          &nbsp; First group project, this application simulated a vending machine. Pretty straightfoward concept, but we
          made great use of Object Oriented principles to future proof our version, allowing customizable classes that
          allowed hypothetical
          vendors to load any type of inventory into the machine and vend it. Flat File memory management, good logging
          practices, and accessibility were our chief concerns, and I would say this project turned out extremely well.
        </p>
      </div>

      <div class="box4">
        <h2>Personal Projects</h2>

        <h3>PKMN Damage Calculator</h3>
        <p>
          &nbsp; This application is a demonstration of a very popular game's damage engine, reverse engineered by myself
          and coded entirely from scratch. I had to manually truncate certain data to properly simulate the hardware on a
          Gameboy Color in order to return accurate results.
        </p>

        <h3>ktstevick.com</h3>
        <p>
          &nbsp; This site was built with Vue3, with custom components from here, and
          utilizes axios for 3rd party API calls. There's only one at the moment - click on the purple box to
          receive a random "Dad Joke" from this API.
        </p>

        <h3>GemQuest</h3>
        <p>
          &nbsp; A classic RPG with a few twists, currently in development by Ian Bird (whose GitHub can be found here)
          and myself. There is a LOT more to say about this and a playable demo on the way, so please check it out on
          GitHub!
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
  height: 20vh;
  width: auto;
  margin-top: 2vh;
  grid-area: intro;

  border-radius: 5px;
}

.box1 h1 {
  font-weight: bold;

  padding: 10px;
  border: 2px solid black;
  border-radius: 5px;
  margin-bottom: 15px;

  color: black;
  background-color: rgba(0, 0, 0, 0.5);
}

.box1 p {
  font-size: 1em;

  margin-left: 15px;
  margin-right: 15px;
}

.box2 {
  height: 60vh;
  width: 12vw;

  margin-left: .25vw;
  margin-top: 1.9vh;

  grid-area: link;

  display: grid;

  position: fixed;
  top: 13vh;
  left: 75.5vw;
}

.link {
  height: 12vh;
  position: relative;

  background-color: hsla(263, 74%, 47%, 0.726);
  border-radius: 5px;
}

.box3 {
  height: 47vh;
  margin-top: 10px;
  grid-area: capstone;

  border-radius: 5px;
}

.box4 {
  height: 47vh;
  margin-top: 10px;
  grid-area: personal;

  border-radius: 5px;
}

main {
  margin-left: 3vw;
  margin-top: auto;
  margin-bottom: auto;
}

h2 {
  font-weight: bold;

  margin-top: 10px;
  padding: 10px;
  border: 2px solid black;
  border-radius: 5px;

  color: black;
  background-color: rgba(0, 0, 0, 0.5);
}

h3 {
  color: black;

  margin-top: 10px;
  padding: 10px;

  border-top-left-radius: 10px;
  border-top-right-radius: 10px;

  font-weight: bold;

  background-color: rgba(0, 0, 0, 0.3);
}

p {
  padding: 10px;

  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;

  background-color: rgba(0, 0, 0, 0.1);
}

.box1 p:first-of-type {
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  border-bottom-right-radius: 0px;
  border-bottom-left-radius: 0px;

  color: black;
  background-color: rgba(0, 0, 0, 0.25);
}

.box1 p {
  border-top-right-radius: 0px;
  border-top-left-radius: 0px;
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;

  color: black;
  background-color: rgba(0, 0, 0, 0.25);
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
  font-size: 3vh;

  padding-top: 3.75vh;
  padding-bottom: 4.25vh;
}

span.text-context:hover {
  opacity: 1;

}
</style>
  