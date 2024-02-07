<!-- This is it! -->

<!-- Starting with Desktop design, but even still, I have to take some measures for smaller setups. I NEED to find a way to lock the wrapper's width so it never gets smashed in... -->
<!-- And, ultimately, this whole thing probably gets set to flex for tablets and changing sizes (until you hit Mobile dimensions, of course)-->

<template>
  <header>
    <div class="wrapper">
      <div id="topHalf">
        <h1 v-on:click="returnHome">KT Stevick</h1>
        <h2>artist</h2>
      </div>

      <div id="bottomHalf">
        <nav>
          <RouterLink to="/portfolio">Portfolio</RouterLink>
          <RouterLink to="/about">About</RouterLink>
        </nav>

        <div id="mediaLinks">
          <img class="linkImage" src="../src/assets/icons/instaicon.png" alt="insta" v-on:click="toInstagram" />
          <img class="linkImage" src="../src/assets/icons/githubicon.png" alt="gitHub" v-on:click="toGitHub" />
          <img class="linkImage" src="../src/assets/icons/linkedinicon.png" alt="linkedIn" v-on:click="toLinkedIn" />
        </div>
      </div>
    </div>
  </header>

  <div class="routerView">
    <RouterView />
  </div>
</template>

<script>
import { RouterLink, RouterView } from 'vue-router'

export default {
  components: {
    RouterLink,
    RouterView
  },

  methods: {
    returnHome() {
      this.$router.push({ name: 'home' });
    },

    // Links to external but relevant sites
    toInstagram() {
      window.location.href = 'https://www.instagram.com/ktstevick/';
    },
    toGitHub() {
      window.location.href = 'https://github.com/ktstevick/ktstevick';
    },
    toLinkedIn() {
      window.location.href = 'https://www.linkedin.com/in/ktstevick/';
    }
  }
}
</script>

<style scoped>
/* It's very likely I'll have to make this header a fixed length rather than a function of view width in the future */
.routerView {
  width: 60vw;
  position: absolute;
  top: 13vh;
  left: 550px;
}

h1 {
  display: inline;
  font-size: 2.5rem;
}

h1:hover {
  cursor: pointer;
  color: rgb(102, 102, 102);
}

h2 {
  display: inline;
  text-align: right;
}

#topHalf {
  display: grid;
  grid-template-columns: 2fr 1fr;
  align-items: baseline;

  width: 375px;
  height: 60px;

  border-bottom: 2px solid gray;

  position: fixed;
  top: 43vh;
  left: 6vw;
}

#bottomHalf {
  display: grid;
  grid-template-columns: 1fr 2fr;
  width: 375px;
  height: 60px;

  position: fixed;
  /* top: (43vh + 50px); */
  left: 6vw;
}

/* THESE control the nav options beneath the title card, I like them and don't plan to alter them much */
nav {
  width: 175px;
  text-align: left;
  margin-top: 5px;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

#mediaLinks {
  text-align: right;
}

.linkImage {
  height: 25px;
  width: 25px;
  margin: 5px;

  cursor: pointer;
}
</style>
