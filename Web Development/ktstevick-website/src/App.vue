<!-- Idle Thoughts: tablet/mobile banner should be a fixed size - people may want to tilt their phones sideways to see the images more closely!
                    The title card doesn't NEED to float on the desktop - would making it fixed and setting up a grid like 1fr 3fr, between title and router work? -->

<template>
  <header>
    <div id="headerTop">
      <h1 v-on:click="this.$router.push({ name: 'home' })">KT Stevick</h1>
      <h2 v-if="!smallScreen">artist</h2>
    </div>

    <div id="headerBottom">
      <nav>
        <div class="routerLink">
          <RouterLink to="/portfolio">Portfolio</RouterLink>
        </div>
        <div class="routerLink">
          <RouterLink to="/about">About</RouterLink>
        </div>
        <div class="routerLink" v-if="smallScreen">
          <RouterLink to="/">Home</RouterLink>
        </div>
      </nav>

      <div v-if="!smallScreen">
        <a class="linkImage" href="https://www.instagram.com/ktstevick/">
          <img class="linkImage" src="../src/assets/icons/instaicon.png" alt="insta" />
        </a>
        <a class="linkImage" href="https://github.com/ktstevick/ktstevick">
          <img src="../src/assets/icons/githubicon.png" alt="gitHub" />
        </a>
        <a class="linkImage" href="https://www.linkedin.com/in/ktstevick/">
          <img src="../src/assets/icons/linkedinicon.png" alt="linkedIn" />
        </a>
      </div>
    </div>
  </header>

  <div id="routerView">
    <RouterView />
  </div>

  <div id="footer" v-if="smallScreen">
    <a class="linkImage" href="https://www.instagram.com/ktstevick/">
      <img class="linkImage" src="../src/assets/icons/instaicon.png" alt="insta" />
    </a>
    <a class="linkImage" href="https://github.com/ktstevick/ktstevick">
      <img src="../src/assets/icons/githubicon.png" alt="gitHub" />
    </a>
    <a class="linkImage" href="https://www.linkedin.com/in/ktstevick/">
      <img src="../src/assets/icons/linkedinicon.png" alt="linkedIn" />
    </a>
  </div>
</template>

<!-- Pretty much every page uses the standard <script>: here, however, I opted for <script setup> so I could access this smallScreen trait and
     v-if elements out of the DOM in real time as the window size adjusts. I'm pretty sure I could have accomplished the same thing with a setup
     function of some kind, but I didn't need the exports or named methods here anyways, so it seemed like the most efficient choice. This note 
     is mostly for myself - it looked so smooth/natural, it only took fifteen minutes to forget WHY I overhauled the whole thing. -->
<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { useMatchMedia } from './store/misc.js'

const smallScreen = useMatchMedia('(max-width: 1179px)');
</script>

<style scoped>
/* Consolidating styles between sizes here */
header {
  background-color: rgb(245, 245, 245);

  height: 11vh;
  width: 100vw;
  border-bottom: 2px solid black;

  top: 0px;
  left: 0px;

  position: fixed;
  z-index: 100;

  display: flex;
  text-align: center;
}

#headerTop {
  display: flex;
}

#headerTop h1 {
  margin: 0 0 1vh 5vw;

  font-size: 2.5em;
  align-self: flex-end;
}

#headerBottom {
  margin: 0 0 0 auto;
}

#routerView {
  /* background-color: rgb(211, 192, 255); */

  padding-top: 11vh;
}

#footer {
  height: 12vh;

  margin: 1vh 9.5vw 0 9.5vw;
  border-top: 2px solid black;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  align-items: start;
  justify-items: center;

  /* opacity: .25; */
}

/* Navigation */
nav {
  height: 11vh;
  width: 45vw;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
}

.routerLink {
  background-color: hsla(241, 100%, 50%, 0.1);

  height: 11vh;
  border-bottom: 2px solid black;

  display: flex;
}

.routerLink:nth-of-type(2) {
  background-color: hsla(241, 100%, 50%, 0.3);
}

.routerLink:nth-of-type(3) {
  background-color: hsla(241, 100%, 50%, 0.5);
}

/* Actual links */
#headerBottom nav div a {
  margin: 0px auto 0px auto;

  font-size: 3vw;
  align-self: flex-end;
}

.linkImage {
  height: 12vh;
  width: 27vw;

  text-align: center;
  cursor: pointer;
}

.linkImage img {
  margin-top: 1vh;
  height: 7vh;
  width: 7vh;
}

/* Small devices (Phones below 600px) */
@media only screen and (max-width: 600px) {
  nav a {
    font-weight: bold;
  }
}

/* Large phones and tablets (above 600px) */
@media only screen and (min-width: 600px) {
  #headerTop h1 {
    font-size: 3.5em;
  }
}

/* Large tablets and all desktops (above 1180px) */
@media only screen and (min-width: 1180px) {
  header {
    width: 24vw;
    margin: 0 .5vw;

    border-bottom: 0px;

    top: 45%;

    display: block;
  }

  #headerTop {
    max-width: 325px;

    margin: 0 auto;
    border-bottom: 2px solid black;

    display: flex;
  }

  #headerTop h1 {
    margin: 0 auto 0 0;

    font-size: 2.5em;
    transition: 0.4s;
  }

  #headerTop h1:hover {
    cursor: pointer;
    color: rgb(94, 49, 255);
  }

  #headerTop h2 {
    color: rgb(102, 102, 102);

    /* Fixed number derived from h1 font size */
    padding-top: 18px;
  }

  #headerBottom {
    max-width: 325px;
    margin: 5px auto;
    border: 0;

    display: flex;
  }

  #headerBottom nav {
    margin: 2px auto auto 5px;
  }

  .routerLink {
    background-color: rgb(245, 245, 245);

    height: 23px;
    border-bottom: 0;
  }

  .routerLink:nth-of-type(2) {
    background-color: rgb(245, 245, 245);
    border-left: 1px solid hsla(241, 100%, 50%, 0.5);
  }

  nav a {
    padding: 0 12px;
    border-bottom: 0;

    font-size: 1.1em;
  }

  nav a:first-of-type {
    border: 0;
  }

  nav a:hover {
    background-color: hsla(241, 100%, 50%, 0.2);
  }

  #headerBottom div {
    display: flex;
  }

  /* Adjust text size of site links */
  #headerBottom nav div a {
    font-size: 1.1em;
  }

  .linkImage {
    height: 25px;
    width: 25px;

    margin-top: 0;
    margin-right: 7px;
  }

  .linkImage img {
    height: 25px;
    width: 25px;

    margin-top: 0;
  }

  #routerView {
    /* background-color: rgba(197, 146, 255, 0.5); */

    width: 68vw;
    height: 90vh;

    padding: 0;
    margin: 5vh 0 5vh 27vw;
  }
}
</style>
