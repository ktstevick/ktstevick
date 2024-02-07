<!-- Full credit to -- https://ismail9k.github.io/vue3-carousel/getting-started.html -->

<!-- The only thing I'd like to implement in the future is the ability to click a big_image and have it full screen to the original image's dimensions. But that's hardly a priority -->

<template>
  <!-- BIG CAROUSEL -->
  <Carousel id="gallery" :items-to-show="1" :wrap-around="false" v-model="currentSlide">
    <Slide v-for="piece in gallery" :key="piece.id">
      <img class="big_image" v-bind:src="gallery[piece.id - 1].image" />
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
  <!-- Extra space for readability -->
    <Slide v-for="piece in gallery" :key="piece.id">

      <div class="little_carousel_item" @click="slideTo(piece.id - 1)">

        <img class="little_image" v-bind:src="gallery[piece.id - 1].image" />

      </div>
    
    </Slide>
  </Carousel>
</template>

<script>
import { defineComponent } from 'vue'
import { Carousel, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'GalleryOne',

  props: {
    gallery: { type:Array, required:true }
  },

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
  .carousel__slide {
    padding: 10px;
  }

.big_carousel_item {
  min-height: 55vh;
    width: 100%;

    color: var(--vc-clr-white);
    font-size: 20px;
    
    display: flex;
    justify-content: center;
    align-items: center;
}

.big_image {
  height: 55vh;
  width: 100%;

  border-top: 10px solid black;
  border-right: 10px solid black;
  border-left: 10px solid rgb(100,100,100);
  border-bottom: 10px solid rgb(100, 100, 100);
  border-radius: 2px;
}

.little_carousel_item {
  max-height: 15vh;
    width: 100%;

    color: var(--vc-clr-white);
    font-size: 20px;
    
    display: flex;
    justify-content: center;
    align-items: center;
}

.little_image {
  height: 15vh;
  width: 100%;
  
  object-fit: cover;

  border-radius: 10px;
}

.little_image:hover {
  cursor: pointer;
}
</style>
