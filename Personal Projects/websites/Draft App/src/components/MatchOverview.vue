<!-- Right now we're just passing along the week number -->
<template>
    <h1>vs. {{ this.$store.state.coaches[weekNo].name }}</h1>
    <h2>Week {{ this.weekNo }}</h2>

    <div class="icon" v-for="pokemon in this.$store.state.coaches[weekNo].roster" v-bind:key="name">
            <img v-bind:src="pokemon.icon" />
    </div>

    <h2>At a Glance</h2>
    <p v-for="paragraph in this.$store.state.coaches[weekNo].summary">{{ paragraph }}</p>
</template>

<script>
export default {
    props: {
        weekNo: Number
    }
}
</script>

<style scoped>
.icon {
    display: inline;
}
</style>