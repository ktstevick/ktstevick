<!-- Full credit to -- https://ismail9k.github.io/vue3-carousel/getting-started.html -->

<template>
  <Carousel :items-to-show="2" :wrap-around="true">
    <Slide v-for="piece in gallery" :key="piece.id">
      <div class="carousel__item">

        <img class="little_image" v-bind:src="gallery[piece.id - 1].image" />

      </div>
    </Slide>

    <template #addons>
      <Navigation />
    </template>
  </Carousel>
</template>

<script>
import { defineComponent } from 'vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'WrapAround',

  props: {
    gallery: { type:Array, required:true }
  },

  components: {
    Carousel,
    Slide,
    Navigation,
  },
})
</script>
  
  <style scoped>
  .carousel__item {
    min-height: 20vh;
    width: 100%;

    color: var(--vc-clr-white);
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;

  }
  
  .carousel__slide {
    padding-left: 10px;
    padding-right: 10px;
  }
  
  .carousel__prev,
  .carousel__next {
    box-sizing: content-box;
    border: 5px solid white;
  }

  .little_image {
    height: 20vh;
    width: 100%;
  
  object-fit: cover;

  border-radius: 10px;
  }
  </style>