<!-- Full credit to -- https://ismail9k.github.io/vue3-carousel/getting-started.html -->

<!-- IDLE THOUGHTS: would love to have the img title change based on some quality in the store. Clicking the image and having it display fullscreen (a la MBK) would also be great. -->

<template>
  <Carousel :items-to-show="1.5" :wrap-around="true">
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
    gallery: { type: Array, required: true }
  },

  components: {
    Carousel,
    Slide,
    Navigation,
  },
})
</script>
  
<style scoped>
/* Consolidated */
.carousel__slide {
  padding-left: 10px;
  padding-right: 10px;
}

.little_image {
  height: 65vw;
  width: 65vw;
  border-radius: 10px;

  object-fit: cover;
}

/* Small devices (Phones below 600px) */
@media only screen and (max-width: 600px) {}

/* Large phones and tablets (above 600px) */
@media only screen and (min-width: 600px) {}

/* Large tablets and all desktops (above 1024px) */
@media only screen and (min-width: 1024px) {
  .carousel__item {
    width: 100%;
  }

  /* Width gets redefined back to 100% here */
  .little_image {
    height: 30vh;
    width: 100%;
  }
}
</style>