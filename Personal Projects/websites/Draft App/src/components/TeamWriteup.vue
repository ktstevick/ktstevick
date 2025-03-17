<template>
    <h2>Teambuilding</h2>

    <div class="note" v-for="note in this.$store.state.coaches[weekNo].teamNotes" >
        <div class="icon" v-for="iconID in note.pokemon" >
            <img v-bind:src="this.$store.state.coaches[0].roster[iconID].icon" />
        </div>
        
        <p v-for="paragraph in note.text">{{ paragraph }}</p>
    </div>

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