<template>
  <main>
    <DetailView :id="$route.params.id"/>
  </main>
</template>

<script setup>
import DetailView from '../components/PokemonDetail.vue';
</script>