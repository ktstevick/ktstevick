<template>
  <main>
    <div id="intro">
      <h1>HOME</h1>
      <hr>
      <p>This is the Home Page! Welcome to the party.</p>
      <p>This space is for general introduction and/or updates.</p>
      <p>Head on over to <RouterLink to="/list">List</RouterLink> to see all the Pokemon, or straight to <RouterLink
          to="/calc/1">Calc</RouterLink> to get cooking!</p>
    </div>

    <div id="about">
      <h1>ABOUT</h1>
      <hr>
      <p>More details available on my GitHub README, but the short version is that this app aims to let users read parts
        of the PokeAPI and use this data to check damage calculations against standard and custom sets.</p>
      <p>On a technical side, the rolls themselves may not be 100% accurate. The math isn't terribly complicated but
        definitely more nuanced than it seems. On a similar note, some features of the battle system simply haven't been
        implemented yet:</p>
      <li>Items</li>
      <li>Weather</li>
      <li>Critical Hits</li>
      <li>Multi-hit Moves</li>
      <li>Spread Damage</li>
      <li>Generational Gimmicks</li>
      <li>Et Alia</li>
      <p>As one might imagine, Pokemon was developed by teams of highly trained (and paid) professionals over the course
        of decades, and even most fan projects are extremely well put together. As much as I'd love to implement these
        features, this is just a fun app I made in my free time. Thanks for dropping by!</p>
    </div>

    <div id="links">
      <h1>LINKS</h1>
      <hr>
      <p>See also:</p>
      <a href="https://bulbapedia.bulbagarden.net/wiki/Main_Page">Bulbapedia</a>
      <p>Great source for additional general information about Pokemon, the games, and more.</p>
      <a href="https://www.smogon.com/">Smogon</a>
      <p>The definitive resource for competitive Pokemon, including sets, formulas, and usage statistics.</p>
    </div>
  </main>
</template>

<script>
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
  border-radius: 10px;
}

#about {
  background-color: rgb(180, 238, 195);
  grid-area: about;
  border-radius: 10px;
}

#links {
  background-color: rgb(157, 211, 171);
  grid-area: links;
  border-radius: 10px;
}

#links a {
  font-size: large;
  font-weight: bold;
  padding: 10px;
}

h1 {
  padding-left: 10px;
}

p {
  padding: 10px;
}

li {
  padding: 10px;
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