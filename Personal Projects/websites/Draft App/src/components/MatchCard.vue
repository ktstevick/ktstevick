<template>
    <!-- The data displayed here is fetched by week number for now -->
    <div id="match-card">
        <MatchOverview :weekNo=this.weekNo />
        <TeamWriteup :weekNo=this.weekNo />
        <MatchReview :weekNo=this.weekNo />
    </div>
</template>

<script>
import MatchOverview from './MatchOverview.vue';
import TeamWriteup from './TeamWriteup.vue';
import MatchReview from './MatchReview.vue';

export default {
    props: {
        weekNo: Number
    },

    components: {
        MatchOverview,
        TeamWriteup,
        MatchReview
    }
}
</script>

<style scoped>
#match-card {
    background-color: rgb(30, 40, 40);

    margin: 2vh 0vw;

    border-radius: 10px;
}
</style>