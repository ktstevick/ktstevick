<template>

<forum-post v-bind:post="post" />

</template>

<script>
import ForumService from '../services/ForumService';
import ForumPost from '../components/ForumPost.vue';

export default {
    data() {
        return {
            post: {}
        }
    },
    components: {
        ForumPost
    },
    created() {
        ForumService.getPostById(this.$route.params.postid)
        .then( response => {
            this.post = response.data;
        })
    },
}
</script>