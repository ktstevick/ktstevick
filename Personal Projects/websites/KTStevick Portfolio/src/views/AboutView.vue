<!-- I could spend a week refining this page alone, but I think this is a good place to start. MVP! -->

<template>
  <main>
    <div class="container">
      <div class="intro">
        <h2>About Me:</h2>
        <p>
          &nbsp; My name is Kurtis Stevick, but I've been going by KT a bit more recently. Artistic, autistic, avant-garde
          and
          atavistic - I'm a hard working, fast learning person who enjoys being challenged more than I should admit.
          I received my BFA from the American Academy of Art in Chicago, IL, but have lived and painted all over the
          country. I recently completed a programming bootcamp with Tech Elevator, and they deserve plenty of credit for
          the
          software I've developed - including this site itself! You can read more about those projects on the <RouterLink
            to="/portfolio/software">Software</RouterLink>
          page. My artwork is up on the <RouterLink to="/portfolio/finearts">Fine Arts</RouterLink> page and
          <RouterLink to="/portfolio/illustration">Illustration</RouterLink> page. I'll be adding more of my work as I
          improve the site over time. I like to think my work speaks for itself, but my Mission Statement may shed some
          light:
        </p>
      </div>

      <img v-if="!smallScreen" class="portrait" src="../assets/images/selfie.jpg" title="A man in search of a vision." />

      <div class="statement">
        <p>
          &nbsp;"I am here to understand, honor, and upend as much of the world as I can get my hands on.
          The things that strike me drive me, and I strive to strike back, coming to terms with my environment as much as
          it must come to terms with me. The things people have never seen, the things people haven't yet considered, and
          the things people don't dare dream about - I believe I can show them, or at least see more of them for myself."
        </p>
      </div>

      <img v-if="!smallScreen" class="selfPortrait" src="../assets/images/selfie2.jpg" title="Candor in the face of adversity." />
      <img v-if="smallScreen" class="portrait" src="../assets/images/selfie.jpg" title="A man in search of a vision." />

      <div v-if="!smallScreen" class="outro">
        <p>
          &nbsp; Which maybe sounds like a lot of nonsense, but the gist is that we live
          in an incredible world and I'm very much into it. The more I observe the bigger it feels, and I'm always happy
          to share that with others when I get the chance. I had half a mind to write everything in third person, but too
          much of the site sounds like me already. Maybe once I have enough reviews or critiques from others I'll share
          their thoughts about me instead of my own. For now, it's just me though. Most recent self portrait provided. I
          could write about my values and beliefs (and, really, about ART all day), as well as my hobbies - but perhaps
          another time.
          I think I've given a pretty comprehensive view of myself
          professionally speaking. Thanks for dropping by, and I hope you enjoy the rest of your time here!
        </p>
      </div>
    </div>
  </main>
</template>

<script setup>
import { useMatchMedia } from '../store/misc.js'

const smallScreen = useMatchMedia('(max-width: 1179px)');
</script>

<style scoped>
/* Consolidated */
img {
  height: 60vh;
  width: 100%;
  margin: 2vh 0;

  object-fit: cover;
}

h2 {
  background-color: rgb(255, 215, 200);
  font-size: 2em;

  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;

  padding-left: 20px;
}

p {
  font-size: 1.3em;
  padding: 20px;
}

.intro {
  margin-top: 3vh;
}

.statement p {
  padding: 10px 30px 30px 30px;
  font-style: italic;
}

/* Small devices (Phones below 600px) */
@media only screen and (max-width: 600px) {}

/* Large phones and tablets (above 600px) */
@media only screen and (min-width: 600px) {
  img {
    width: 60%;
    margin: 7.5vw 20vw;
  }

  .intro {
    background-color: rgb(255, 235, 220);

    margin: 3vh 3vh 0 3vh;
    border-top-left-radius: 15px;
    border-top-right-radius: 15px;
  }

  .intro h2 {
    background-color: rgb(230, 189, 169);

    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
  }

  .statement {
    background-color: rgb(255, 235, 220);

    margin: 0 3vh 0 3vh;
    border-bottom-left-radius: 15px;
    border-bottom-right-radius: 15px;
  }
}

/* Large tablets and all desktops (above 1180px) */
@media only screen and (min-width: 1180px) {
  .container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas:
      "formal formal selfie"
      "drawing mission selfie"
      "drawing informal informal";
  }

  img {
    width: 100%;

    margin: 0;
  }

  .intro {
    height: 30vh;
    width: auto;
    border-top-left-radius: 25px;
    margin: 0;

    grid-area: formal;
  }

  .portrait {
    height: 60vh;
    grid-area: selfie;
  }

  .statement {
    background-color: rgb(255, 225, 210);
    height: 30vh;
    margin: 0;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;

    grid-area: mission;
  }

  .selfPortrait {
    grid-area: drawing;
  }

  .outro {
    background-color: rgb(255, 215, 200);

    height: 30vh;
    border-bottom-right-radius: 25px;

    grid-area: informal; 
  }

  .intro h2 {
    background: none;

    margin: 1vh;
    border-bottom: 2px solid black;
    padding: 0;

    width: 170px;

    font-size: 32px;
    text-align: center;
  }

  p {
    margin: 3vh 1.2vw;
    padding: 0;

    display: inline-block;
    vertical-align: middle;

    line-height: 3.25vh;
    font-size: 0.7vw;
  }

  .intro p {
    margin-top: 1vh;
  }

  .statement p {
    padding: 0;
  }

  span {
    color: rgb(94, 49, 255);
    transition: 0.4s;

    padding: 2px;
  }

  span:hover {
    text-decoration: none;
    background-color: hsla(241, 100%, 50%, 0.2);

    cursor: pointer;
  }
}
</style>
