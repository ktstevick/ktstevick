<template>
  <main>
    <div id="intro">
      <h1>HOME</h1>
      <hr>
      <p>This is the Home Page! Welcome to the party.</p>
      <p>This is where the general introductions will go. I'll explain a bit about the project maybe? Not totally sure
        yet.</p>
    </div>

    <div id="about">
      <h1>ABOUT</h1>
      <hr>
      <p>This is the About section. It's about exactly what you might imagine.</p>
      <p>More for technical stuff too - like exactly what is an isn't being factored into calculations, etc.</p>
    </div>

    <div id="links">
      <h1>LINKS</h1>
      <hr>
      <p>This area is for Links.</p>
      <p>I'm thinking one for GitHub, one for Smogon, one for Bulbapedia, et alia.</p>
    </div>
  </main>
</template>

<script setup>
</script>

<style scoped>
main {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "intro intro links"
    "about about links";
}

main div {
  margin: 1vh 1vw;
}

#intro {
  background-color: rgb(177, 255, 197);
  grid-area: intro;
}

#about {
  background-color: rgb(160, 231, 178);
  grid-area: about;
}

#links {
  background-color: rgb(135, 196, 150);
  grid-area: links;
}

@media only screen and (max-width: 768px) {
  main {
    grid-template-columns: 1fr;
    grid-template-areas:
      "intro"
      "about"
      "links";
  }
}
</style>