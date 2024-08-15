<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125"
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
    <hr>
    <p>FOOTER TEST TEXT</p>
  </footer>
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
  background-color: rgb(238, 238, 238);
}

nav {
  background-color: rgb(238, 210, 210);
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.RouterLink {
  margin: 0px 10px;
  text-decoration: none;
}

/* Includes vertical tablets */
@media only screen and (max-width: 768px) {
  header {
    background-color: pink;
  }
}
</style>
