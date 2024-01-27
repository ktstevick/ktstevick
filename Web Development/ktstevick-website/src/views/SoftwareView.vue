<!-- This one is a little different from the others, since programs don't necessarily have or NEED images -->
<!-- I'd like to havea  brief description of each project IN my GitHub, as well as a video demo of Plants and GemQuest at some point -->
<!-- In addition, this page will include the JavaScript demos for fun. Right now I just want to connect to a 3rd party API. -->

<!-- So, what do I need to make this happen? -->

<!-- Let's start with the obvious - what projects ARE in your GitHub? It makes sense to break things into categories, I think. -->

<!-- PERSONAL PROJECTS -->
    <!-- A* Demo -->
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

<template>
    <main>
    <div class="container">
      <div class="box1">INTRO/EXPLANATION</div>
      <div class="box2">LINK(S)</div>
      <div class="box3">PERSONAL PROJECTS</div>
      <div class="box4">TE CAPSTONES</div>
      <div class="box5">JS APPS</div>
    </div>
  </main>
  </template>
  
  <script>
  
  </script>
  
  <style scoped>
  .container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: 
    "intro intro link"
    "capstone personal jsApps"
    "capstone personal jsApps";
  gap: 6vh;
  }
  
  .box1 {
  background-color: hsl(0, 0%, 90%);
  height: 20vh;
  width: auto;
  margin-top: 10px;
  grid-area: intro;
  }
  
  .box2 {
    background-color: hsl(0, 0%, 80%);
  height: 20vh;
  margin-top: 10px;
  grid-area: link;
  }
  
  .box3 {
    background-color: hsl(0, 0%, 70%);
  height: 47vh;
  margin-top: 10px;
  grid-area: personal;
  }
  
  .box4 {
    background-color: hsl(0, 0%, 60%);
  height: 47vh;
  margin-top: 10px;
  grid-area: capstone;
  }
  
  .box5 {
    background-color: hsl(0, 0%, 50%);
  height: 47vh;
  margin-top: 10px;
  grid-area: jsApps;
  }
  
  main {
  /* background-color: pink; */
  margin-left: 3vw;
  margin-top: auto;
  margin-bottom: auto;
  }
  </style>
  