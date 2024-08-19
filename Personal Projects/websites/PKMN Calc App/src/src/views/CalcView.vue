<template>
  <main>
    <CalcSummary :customID="$route.params.id"/> <!-- Possibly a better way to do this -->
  </main>
</template>

<script setup>
import CalcSummary from '../components/CalcSummary.vue';
</script>