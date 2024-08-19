<!-- Idle Thoughts: tablet/mobile span transitions occur on scroll? Probably a pretty involved process, would be nice to incorporate the spans to their full potential on all views, though. -->
<!--                Looks good, but programmatically I'd like to refine how the navigation itself is handled -->

<template>
  <main>
    <div class="container">
      <div class="bannerBox" v-on:click="toFineArts">
        <img class="banner" src="../assets/images/banner1.jpg" />
        <span class="text-context">
          <h1>Fine Arts</h1>
        </span>
      </div>

      <div class="bannerBox" v-on:click="toIllustration">
        <img class="banner" src="../assets/images/banner2.jpg" />
        <span class="text-context">
          <h1>Illustration</h1>
        </span>
      </div>

      <div class="bannerBox" v-on:click="toSoftware">
        <img class="banner" src="../assets/images/banner3.jpg" />
        <span class="text-context">
          <h1>Software</h1>
        </span>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  methods: {
    toFineArts() {
      this.$router.push({ name: 'fineArts' });
    },
    toIllustration() {
      this.$router.push({ name: 'illustration' });
    },
    toSoftware() {
      this.$router.push({ name: 'software' });
    }
  }
}
</script>

<style scoped>
/* Consolidated */
.banner {
  height: 20vh;
  width: 100%;

  object-fit: cover;
}

.banner:hover {
  cursor: pointer;
}

.text-context {
  text-align: center;
}

.text-context h1 {
  font-size: 3vh;
  letter-spacing: 10px;

  padding-bottom: 3vh;
}


/* Small devices (Phones below 600px) */
@media only screen and (max-width: 600px) {}

/* Large phones and tablets (above 600px) */
@media only screen and (min-width: 600px) {}

/* Large tablets and all desktops (above 1180px) */
@media only screen and (min-width: 1180px) {
  .container {
    /* Percents don't work here - these values are the same as #routerView in App.vue */
    width: 68vw;
    height: 90vh;

    padding: 7.5vh 0;
  }

  .bannerBox {
    height: 25vh;

    position: relative;
    display: flex;
    /* background-color: rgb(177, 128, 177); */
  }

  .banner {
    height: 20vh;
    width: 100%;
    border-radius: 10px;

    align-self: center;
  }

  .text-context {
    background-color: rgba(0, 5, 30, 0.5);
    color: white;
    cursor: pointer;

    height: 20vh;
    width: 100%;

    opacity: 0;
    -webkit-transition: opacity 500ms;
    -moz-transition: opacity 500ms;
    -o-transition: opacity 500ms;
    transition: opacity 500ms;

    position: absolute;
    top: 50%;
    left: 50%;

    transform: translate(-50%, -50%);

    text-align: center;

    border-radius: 10px;
  }

  .text-context h1 {
    font-size: 6vh;
    letter-spacing: 20px;

    padding-top: 6vh;
    padding-bottom: 6vh;
  }

  span.text-context:hover {
    opacity: 1;
  }
}
</style>
