<!-- Full credit to -- https://ismail9k.github.io/vue3-carousel/getting-started.html -->

<template>
  <!-- BIG CAROUSEL -->
  <Carousel id="gallery" :items-to-show="1" :wrap-around="false" v-model="currentSlide">
    <Slide v-for="slide in 7" :key="slide">
      <div class="big_carousel_item">{{ slide }}</div>
    </Slide>
  </Carousel>

  <!-- LITTLE CAROUSEL -->
  <Carousel
    id="thumbnails"
    :items-to-show="3"
    :wrap-around="false"
    v-model="currentSlide"
    ref="carousel"
  >
    <Slide v-for="slide in 7" :key="slide">
      <div class="little_carousel_item" @click="slideTo(slide - 1)">{{ slide }}</div>
    </Slide>
  </Carousel>
</template>

<script>
import { defineComponent } from 'vue'
import { Carousel, Slide } from 'vue3-carousel'

import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'GalleryOne',
  components: {
    Carousel,
    Slide,
    // Navigation,
  },
  data: () => ({
    currentSlide: 0,
  }),
  methods: {
    slideTo(val) {
      this.currentSlide = val
    },
  },
})
</script>

<style scoped>
.big_carousel_item {
  min-height: 55vh;
    width: 100%;
    background-color: rgb(255, 151, 168);
    color: var(--vc-clr-white);
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.little_carousel_item {
  min-height: 15vh;
    width: 100%;
    background-color: pink;
    color: var(--vc-clr-white);
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* Doesn't apply to anything in this component, just here as a reference tool for the moment */
.carousel__item {
    min-height: 20vh;
    width: 100%;
    background-color: pink;
    color: var(--vc-clr-white);
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .carousel__slide {
    padding: 10px;
  }
  
  .carousel__prev,
  .carousel__next {
    box-sizing: content-box;
    border: 5px solid white;
  }

</style>
