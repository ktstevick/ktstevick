<template>
    <main>
    <div class="container">
      <div class="box1" v-on:click="toFineArts">FINE ARTS</div>
      <div class="box2" v-on:click="toGraphicDesign">GRAPHIC DESIGN</div>
      <div class="box3" v-on:click="toSoftware">SOFTWARE DEVELOPMENT</div>
    </div>
</main>
</template>

<script>
export default {
  methods: {
    toFineArts() {
      this.$router.push({ name: 'fineArts'});
    },
    toGraphicDesign() {
      this.$router.push({ name: 'graphicDesign'});
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
  gap: 6vh;
}

.box1 {
  background-color: hsl(241, 100%, 86%);
  height: 20vh;
  margin-top: 10px;
}

.box2 {
  background-color: hsl(241, 100%, 82%);
  height: 20vh;
  margin-top: 10px;
}

.box3 {
  background-color: hsl(241, 100%, 75%);
  height: 20vh;
  margin-top: 10px;
}

main {
  /* background-color: pink; */
  margin-left: 3vw;
  margin-top: auto;
  margin-bottom: auto;
}
</style>
