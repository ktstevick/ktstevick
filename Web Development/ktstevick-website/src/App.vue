<template>
  <header>
    <div class="wrapper">
      <div id="topHalf">
        <h1 v-on:click="returnHome">KT STEVICK</h1>
        <h2>artist</h2>
      </div>

      <div id="bottomHalf">
      <nav>
        <RouterLink to="/portfolio">Portfolio</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>

      <div id="mediaLinks">
        <img class="linkImage" src="../src/assets/icons/instaicon.png" alt="insta" />
        <img class="linkImage" src="../src/assets/icons/githubicon.png" alt="insta" />
        <img class="linkImage" src="../src/assets/icons/linkedinicon.png" alt="insta" />
      </div>
    </div>
  </div>
  </header>

  <RouterView />

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
      this.$router.push({ name: 'home'});
    }
  }
}
</script>

<style scoped>
/* It's very likely I'll have to make this header a fixed length rather than a function of view width in the future */
header {
  line-height: 1.5;
  height: 100vh;
  /* background-color: rgb(255, 211, 218); */
}

h1 {
  display: inline;
  font-size: 2.5rem;
}

h1:hover {
  cursor: pointer;
  color:rgb(102, 102, 102);
}

h2 {
  display: inline;
  text-align: right;
}

#topHalf {
  display:grid;
  grid-template-columns: 2fr 1fr;
  align-items: baseline;
  width: 20vw;
  border-bottom: 2px solid gray;
}

#bottomHalf {
  display:grid; 
  grid-template-columns: 1fr 2fr;
  width: 20vw;
}

#mediaLinks {
  text-align: right;
}

.linkImage {
  height: 25px;
  width: 25px;
  margin: 5px;
}

nav {
  width: 10vw;
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

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }


}
</style>
