<!-- I'm going to set this up as a grid and see how the carousel behaves in a naturally smaller box-->

<template>
  <main>
    <div class="container">
      <div class="box">CURRENT PIECE</div>
      <!-- <div class="box">2</div> -->
      <CarouselBase />
      <!-- <div class="box">3</div> -->
      <CarouselBase />
      <div class="box">JUST FINISHED</div>
      <div class="box">RUNNING BLOG</div>
      <div class="box"></div>
    </div>
  </main>
</template>

<script>
import CarouselBase from '../components/CarouselBase.vue'

export default {
  components: {
    CarouselBase
  }
}
</script>

<style scoped>
.container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 5vh;
}

.box {
  background-color: hsla(241, 100%, 50%, 0.2);
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