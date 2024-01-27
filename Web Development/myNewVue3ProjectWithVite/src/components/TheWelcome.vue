<template>
  <div>
    <h1>{{ joke }}</h1>
  </div>
</template>

<script>
import jokeService from '../services/jokeService.js';

export default {
  data() {
    return {
      joke: ''
    }
  },
  created() { // Lifecycle hook, runs methods ON startup (creation)
    jokeService.getRandomJoke()
    .then((response) => {
      console.log(response); // Good habit just to see what comes back
      this.joke = response.data;

    })
  }
}

</script>
