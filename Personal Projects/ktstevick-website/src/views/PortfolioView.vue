<template>
    <main>
    <div class="container">
      <div class="box1" v-on:click="toFineArts">
        <img class="banner" src="../assets/images/banner1.jpg" />
          <span class="text-context">
            <h1>F i n e  &nbsp; A r t s</h1>
          </span>
      </div>

      <div class="box2" v-on:click="toIllustration">
        <img class="banner" src="../assets/images/banner2.jpg" />
        <span class="text-context">
          <h1>I l l u s t r a t i o n</h1>
        </span>
      </div>

      <div class="box3" v-on:click="toSoftware">
        <img class="banner" src="../assets/images/banner3.jpg"/>
        <span class="text-context">
          <h1>S o f t w a r e</h1>
        </span>
      </div>
    </div>
</main>
</template>

<script>
export default {
  methods: {
    toFineArts() {
      this.$router.push({ name: 'fineArts'});
    },
    toIllustration() {
      this.$router.push({ name: 'illustration'});
    },
    toSoftware() {
      this.$router.push({ name: 'software'});
    }
  }
}
</script>

<style scoped>
.container {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 20vh;
  gap: 6vh;
}

.box1 {
  /* background-color: hsl(241, 100%, 86%); */
  height: 20vh;

  position: relative;
}

.banner {
  height: 20vh;
  width: 100%;
  
  object-fit: cover;

  border-radius: 10px;
}

.box2 {
  /* background-color: hsl(241, 100%, 82%); */
  height: 20vh;

  position: relative;
}

.box3 {
  /* background-color: hsl(241, 100%, 75%); */
  height: 20vh;

  position: relative;
}

main {
  /* background-color: pink; */
  margin-left: 3vw;
  margin-top: auto;
  margin-bottom: auto;
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

  display: table;
  text-align: center;

  border-radius: 10px;
}

.text-context h1 {
  font-size: 4em;

  margin-top: 5.5vh;
}

span.text-context:hover  {
  opacity: 1;
}

</style>
