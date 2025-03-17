<template>
    <h2>VOD Review</h2>

    <div class="icon" v-for="iconID in this.$store.state.coaches[weekNo].matchTeam" >
        <img v-bind:src="this.$store.state.coaches[weekNo].roster[iconID].icon" />
    </div>

    <!-- We're skipping turn-by-turn analysis here, I have something in mind for this down the line -->
    <h3>Pokepaste - </h3>
    <h3>Team Preview - </h3>
    <h3>VOD - </h3>
    <h3>Notes - </h3>
    
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