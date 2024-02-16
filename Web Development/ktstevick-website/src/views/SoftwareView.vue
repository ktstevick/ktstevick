<template>
  <main>
    <div class="container">
      <div class="box1">
        <h1>SOFTWARE DEVELOPMENT</h1>
        <p>
          &nbsp; This page is a bit different from the others. My team projects, Tech Elevator capstones, and personal
          projects are all available for review
          on my GitHub page - the link for which is provided here, but it's also accessible from every page on the site.
          These are just a few quick notes to pique
          your interest, get your attention, or otherwise let you know these projects might be worth a closer look.
        </p>
        <p>
          &nbsp; Still not 100% sure how I want to format this page, but for now I think just text should suffice. Maybe
          an image or two. I'll play around with it a bit - that's
          how I like to develop best anyways.
        </p>
      </div>

      <!-- It might be worth making these link straight to the projects, but I don't want to oversaturate the page with redirection - OR reflection. Finding the right balance between saying too much and too little will take time. -->
      <div class="box3">
        <h2>TE Capstones</h2>

        <!-- Pictures from the Green Thumb project -->
        <CarouselBase class="carousel" v-bind:gallery="this.$store.state.green_thumb" />

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

        <h3>ktstevick.com</h3>
        <p>
          &nbsp; This site was built with Vue3, with custom components from <span
            v-on:click="toCarouselSource">here</span>, and
          utilizes axios for 3rd party API calls. There's only one at the moment - click on the purple box to
          receive a random "Dad Joke" from the incredible <span v-on:click="toDadJokeAPI">icanhazdadjoke API.</span> (It
          should be noted that while I may personally find these hilarious, my dad by no means endorses these jokes!)
        </p>

        <h3>PKMN Damage Calculator</h3>
        <p>
          &nbsp; This application is a demonstration of a very popular game's damage engine, reverse engineered by myself
          and coded entirely from scratch. I had to manually truncate certain data to properly simulate the hardware on a
          Gameboy Color in order to return accurate results.
        </p>

        <!-- Pictures from GemQuest -->
        <CarouselBase class="carousel" v-bind:gallery="this.$store.state.gem_quest" />

        <h3>GemQuest</h3>
        <p>
          &nbsp; A classic RPG with a few twists, currently in development by <span v-on:click="toBirdGitHub">Ian
            Bird</span> (whose GitHub is linked)
          and myself. There is a LOT more to say about this and a playable demo on the way, so please check it out on
          GitHub!
        </p>
      </div>

      <!-- Links can all be found here -->
      <div id="linkOne" v-on:click="toGitHub">
        <img class="bannerIcon" src="../assets/icons/githubiconbanner.png" title="gitHub Icon" />
        <span class="text-context">
          <h1>My GitHub</h1>
        </span>
      </div>

      <div id="linkTwo" v-on:click="getDadJoke">
        <span class="text-context">
          <h1>Dad Joke API Call</h1>
        </span>
      </div>

      <div id="linkThree" v-on:click="toBirdGitHub">
        <img class="bannerIcon" src="../assets/icons/githubiconbanner.png" title="gitHub Icon" />
        <span class="text-context">
          <h1>Ian Bird's</h1>
        </span>
      </div>

      <div id="linkFour" v-on:click="toFineArts">
        <img class="banner" src="../assets/images/banner1.jpg" />
        <span class="text-context">
          <h1>Fine Arts</h1>
        </span>
      </div>

      <div id="linkFive" v-on:click="toIllustration">
        <img class="banner" src="../assets/images/banner2.jpg" />
        <span class="text-context">
          <h1>Illustration</h1>
        </span>
      </div>
    </div>
  </main>
</template>
  
<script>
import jokeService from '../services/jokeService.js'
import CarouselBase from '@/components/CarouselBase.vue'

export default {
  components: {
    CarouselBase
  },

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
    toBirdGitHub() {
      window.location.href = 'https://github.com/birdmandeveloper';
    },
    toCarouselSource() {
      window.location.href = 'https://ismail9k.github.io/vue3-carousel/getting-started.html';
    },
    toDadJokeAPI() {
      window.location.href = 'https://icanhazdadjoke.com/api';
    },

    getDadJoke() {
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
  grid-template-areas:
    "linkOne"
    "intro"
    "capstone"
    "linkTwo"
    "personal"
    "linkThree"
    "linkFour"
    "linkFive";
  gap: 2vh;
}

/* TEXT */
.box1 {
  grid-area: intro;
  border-radius: 5px;
}

.box1 h1 {
  text-align: center;
  font-size: 1.7em;
  font-weight: bold;

  padding: 10px;
  border-top: 2px solid black;
  border-bottom: 2px solid black;
  /* margin-bottom: 15px; */

  color: black;
  background-color: rgb(140, 140, 140);
}

.box3 {
  grid-area: capstone;
  border-radius: 5px;
}

.box4 {
  grid-area: personal;
  border-radius: 5px;
}

h2 {
  text-align: center;
  font-size: 1.5em;
  font-weight: bold;

  border-top: 2px solid black;
  border-bottom: 2px solid black;
  padding: 10px;

  color: black;
  background-color: rgb(170, 170, 170);
}

h3 {
  color: black;

  margin: 10px 5px 0 5px;
  padding: 10px;

  border-top-left-radius: 10px;
  border-top-right-radius: 10px;

  font-weight: bold;

  background-color: rgb(200, 200, 200);
}

p {
  margin: 0 5px;
  padding: 5px 10px 10px 10px;

  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: rgb(225, 225, 225);
}

.box1 p:first-of-type {
  margin-top: 2vh;
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  border-bottom-right-radius: 0px;
  border-bottom-left-radius: 0px;

  color: black;
}

.box1 p {
  border-top-right-radius: 0px;
  border-top-left-radius: 0px;
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;

  color: black;
  background-color: rgb(200, 200, 200);
}

/* COMPONENTS */
.carousel {
  width: 100vw;
  margin-top: 15px;
}

/* LINKS */
#linkOne {
  background-color: black;
  height: 12vh;
  margin-top: 2vh;
  position: relative;
  grid-area: linkOne;
}

#linkTwo {
  background-color: rgb(226, 214, 255);
  height: 12vh;
  position: relative;
  grid-area: linkTwo;
}

#linkThree {
  background-color: black;
  height: 12vh;
  position: relative;
  grid-area: linkThree;
}

#linkFour {
  background-color: rgb(255, 214, 221);
  height: 12vh;
  position: relative;
  grid-area: linkFour;
}

#linkFive {
  background-color: rgb(255, 214, 221);
  height: 12vh;
  margin-bottom: 1vh;
  position: relative;
  grid-area: linkFive;
}

.banner {
  height: 12vh;
  width: 100%;

  object-fit: cover;
}

.bannerIcon {
  height: 90%;
  margin: .6vh;
  /* padding-left: 13vw; */
  padding-left: 65%;
}

#linkOne .text-context {
  background-color: rgba(0, 0, 0, 0);
    text-align: left;
    padding-left: 15%;
  }

  #linkThree .text-context {
    background-color: rgba(0, 0, 0, 0);
    text-align: left;
    padding-left: 20%;
  }

.text-context {
  background-color: rgba(0, 5, 30, 0.25);
  color: rgb(245, 245, 245);
  cursor: pointer;

  height: 12vh;
  width: 100%;

  -webkit-transition: opacity 500ms;
  -moz-transition: opacity 500ms;
  -o-transition: opacity 500ms;
  transition: opacity 500ms;

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  text-align: center;
}

.text-context h1 {
  font-size: 4vh;

  padding-top: 3vh;
  padding-bottom: 4vh;
}

.box4 span {
  color: rgb(94, 49, 255);
  transition: 0.4s;

  padding: 2px;
}

.box4 span:hover {
  text-decoration: none;
  background-color: hsla(241, 100%, 50%, 0.2);

  cursor: pointer;
}

/* Small devices (Phones below 600px) */
@media only screen and (max-width: 600px) {}

/* Large phones and tablets (above 600px) */
@media only screen and (min-width: 600px) {
  .text-context h1 {
    font-size: 3vh;

    padding-top: 3.75vh;
    padding-bottom: 4.25vh;
  }

  .bannerIcon {
  padding-left: 55%;
}

#linkOne .text-context {
    padding-left: 30%;
  }

  #linkThree .text-context {
    padding-left: 35%;
  }
}

/* Large tablets and all desktops (above 1180px) */
@media only screen and (min-width: 1180px) {
  .container {
    /* background-color: pink; */

    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas:
      "intro intro"
      "capstone personal"
      "capstone personal"
      "capstone personal"
      "capstone personal"
      "capstone personal";
    /* !!! */
    gap: 1vw;
  }

  .box1 {
    width: 45vw;
  }

  .box1 h1 {
    border-left: 2px solid black;
    border-right: 2px solid black;
    border-radius: 5px;
  }

  .box3 {
    width: 22vw;
    padding-bottom: 12vh;
  }

  .box4 {
    width: 22vw;
    padding-bottom: 12vh;
  }

  h2 {
    border-left: 2px solid black;
    border-right: 2px solid black;
    border-radius: 5px;
  }

  .carousel {
    width: 22vw;
  }

  #linkOne {
    width: 22vw;
    margin-top: 0;
    border-radius: 10px;

    /* Currently eyeballed */
    position: fixed;
    left: 74vw;
    top: 12vh;
  }

  #linkTwo {
    width: 22vw;
    border-radius: 10px;

    position: fixed;
    left: 74vw;
    top: 28vh;
  }

  #linkThree {
    width: 22vw;
    border-radius: 10px;

    position: fixed;
    left: 74vw;
    top: 44vh;
  }

  #linkFour {
    width: 22vw;
    border-radius: 10px;

    position: fixed;
    left: 74vw;
    top: 60vh;
  }

  #linkFive {
    width: 22vw;
    margin-bottom: 0;
    border-radius: 10px;

    position: fixed;
    left: 74vw;
    top: 76vh;
  }

  .banner {
    border-radius: 10px;
  }

  .bannerIcon {
    padding-left: 13vw;
  }

  #linkOne .text-context {
    padding-left: 2vw;
  }

  #linkThree .text-context {
    padding-left: 2vw;
  }

  .text-context {
    opacity: 0;
    border-radius: 10px;

    text-align: left;
    padding-left: 2vw;
  }

  span.text-context:hover {
    opacity: 1;
  }

}
</style>
  