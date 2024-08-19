<template>
  <main>
    <header>
      <img alt="Green Pokedex Painting" class="logo" src="@/assets/images/greentwo.png" width="500" height="250"
        @click="this.$router.push({ name: 'home' })" />

      <div class="wrapper">
        <nav>
          <RouterLink class="RouterLink" to="/" v-if="!smallScreen">Home</RouterLink>
          <RouterLink class="RouterLink" to="/list">List</RouterLink>
          <RouterLink class="RouterLink" to="/calc/1">Calc</RouterLink>
        </nav>
      </div>
    </header>

    <RouterView />

    <footer>
      <a href="https://www.instagram.com/ktstevick/">
          <img src="../src/assets/icons/instaicon.png" alt="insta" />
        </a>
        <a class="linkImage" href="https://github.com/ktstevick">
          <img src="../src/assets/icons/githubicon.png" alt="gitHub" />
        </a>
        <a class="linkImage" href="https://www.linkedin.com/in/ktstevick/">
          <img src="../src/assets/icons/linkedinicon.png" alt="linkedIn" />
        </a>
    </footer>
  </main>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router';
import uTool from './misc/utilityTool.js';

const smallScreen = uTool.useMatchMedia('(max-width: 768px)');
</script>

<style scoped>
/*  There are a few ways I could organize this CSS, but since this application is primarily intended for desktop use (while playing the game),
    I'm going to set the desktop CSS as the base, then adjust to phone sized settings from there. This will be consistent throughout the project. */

header {
  text-align: center;
  margin-left: calc(100vw - 100%);
}

header img {
  /* border: 2px solid black; */
  margin: 10px;
  border-radius: 40px;
}

nav {
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  background-image: linear-gradient(to right, white, rgb(238, 224, 197), white);
}

.RouterLink {
  margin: 0px 10px;
  text-decoration: none;
  color: black;
}

footer {
  text-align: center;
  border-top: 2px solid black;
  margin: 25px auto;
  width: 75vw;
}

footer img{
  height: 75px;
  width: 75px;

  margin: 25px;
}

/* Includes vertical tablets */
@media only screen and (max-width: 768px) {

}
</style>
