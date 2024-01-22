<template>
<div class="wholeView">
    <div class="forumBar">
    <button id="makePostButton" v-on:click="toggleShowForm">SAY SOMETHING</button>

        <div class="postForm" v-if="showForm">
            <input type="text" placeholder="Title" v-model="this.new_post.title"/>
            <input type="Number" placeholder="Price (If Applicable)" v-model="this.new_post.price"/>
            <textarea id="post_description" placeholder="Description" rows="10" cols="45" v-model="this.new_post.post_description">  </textarea>
            <button class="button" v-on:click="submitNewPost">Submit</button>
            <button class="button" v-on:click="cancelPost">Cancel</button>


            <input class="imageSelect" v-if="showFileSelect" type="file" ref="fileInput" @change="uploadImage">
        </div>

    <hr>
    </div>
</div>
<div class="wholePreview">
    <div class="preview" v-for="post in posts" v-bind:key="post.post_id">
        <forum-card v-bind:post="post"/>
    </div>
</div>
</template>

<script>
import forumCard from '../components/ForumCard.vue';
import router from '../router';
import ForumService from '../services/ForumService.js';


export default {

    data() {
        return {
            posts: [],
            showForm: false,
            new_post: {
                user_id : this.$store.state.user.id,
                forum_id : Number(this.$route.params.id),
            }
        }
    },

    components: {
        forumCard
    },
    
    created() {
        ForumService.getPostByForums(this.$route.params.id)
        .then (response => {
            this.posts = response.data;
        })
    },

    methods: {
        createPost() {
            alert(this.$store.state.user.username + ' says - hello!');
        },
        toggleShowForm() {
            this.showForm = !this.showForm;
        },

        cancelPost(){
            this.new_post = {
                user_id : this.$store.state.user.id,
                forum_id : Number(this.$route.params.id)
            };
            this.showForm = false;
        },

        submitNewPost(){
            console.warn(this.new_post)
            ForumService.submitPost(this.new_post)
            this.cancelPost()
            router.go()
        }

    }
}
</script>

<style scoped>

.forumBar {
    margin-top: 10px;
    text-align: center;
}

#makePostButton {
    margin-bottom: 10px;
}

#post_description{
    display: block;
    margin-left: auto;
    margin-right: auto;
}

.wholeView{
    width: 70vw;
}

.wholePreview{
    display: flex;
    flex-wrap: wrap;
}

.button{
    margin-top: 10px;
    margin-left: 5px;
    margin-right: 5px;
    margin-bottom: 5px;
}

</style>