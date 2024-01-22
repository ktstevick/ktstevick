<template>
    <div class="forumCard">
        <div class="cardTop">

            <h2>{{ post.title }}</h2>
            <h3 v-if="post.price != null">${{ post.price }}</h3>
            <p>{{ post.post_description }}</p>
            <!-- <h2 v-if="length < 200">{{ post.post_description }} </h2>
            <h2 v-else> {{ preview }}</h2> -->

            <img class="cardImage" src="../assets/garden.jpg" />

            <div class="buttons">
                <button v-on:click="showReplyForm" class="cardButton" v-if="!showReply">MESSAGE</button>
                <textarea rows="4" cols="40" v-if="showReply" v-model="replyMessage.message_body"></textarea>
                <button v-if="showReply" @click="submitReply">Submit</button>
                <button @click="cancelReply" v-if="showReply">Cancel</button>
            </div>
        </div>
    </div>
</template>

<script>

import MessageService from '../services/MessageService'

export default {
    data() {
        return {
            showReply: false,
            replyMessage: {
                to_user_id: this.post.user_id,
                from_user_id: this.$store.state.user.id
            }
            //    length: this.post.post_description.length,
            //    preview: this.post.post_description.substring(0, 200)
        }
    },
    props: {
        post: { type: Object, required: true },
    },
    methods: {
        pushToMessages() {

        },
        pushToDetails() {
            this.$router.push({ name: 'post', params: { postid: this.post.post_id } })
        },

        showReplyForm() {
            this.showReply = true
        },

        submitReply() {
            MessageService.postMessage(this.replyMessage)
            this.showReply = false,
                this.replyMessage = {
                    to_user_id: this.post.user_id,
                    from_user_id: this.$store.state.user.id
                }
        },

        cancelReply() {
            this.showReply = false,
                this.replyMessage = {
                    to_user_id: this.post.user_id,
                    from_user_id: this.$store.state.user.id
                }
        }
    }
}
</script>

<style scoped>
h2 {
    color: rgb(23, 63, 22);
}

h3 {
    color: rgb(23, 63, 22);
}

p {
    color: rgb(14, 39, 14);
}

.forumCard {
    text-align: left;
    border: 2px solid rgb(23, 63, 22);
    background-color: rgb(228, 255, 228);
    border-radius: 10px;
    width: 70vw;
    height: auto;
    margin: 20px;
    padding: 10px;
}

.cardImage {
    margin-top: 5px;
    width: 70vw;
    height: auto;
}

.cardTop {
    height: auto;
}

.cardTop>h1 {
    font-size: 70%;
    color: darkgreen;
}

.cardButton {
    width: 40vw;
    height: 4vh;

    margin: auto;
    margin-top: 5px;

    background-color: rgb(255, 232, 236);
}

.buttons {
    display: flex;
}

@media screen and (min-width: 600px) {

    .forumCard {
        text-align: left;
        border: 2px solid rgb(23, 63, 22);
        background-color: rgb(228, 255, 228);
        border-radius: 10px;
        width: 20vw;
        height: auto;
        margin: 20px;
        padding: 10px;
    }

    .cardImage {
        margin-top: 5px;
        width: 20vw;
        height: auto;
    }

    .cardTop {
        height: auto;
    }

    .cardTop>h1 {
        font-size: 70%;
        color: darkgreen;
    }

    .cardButton {
        width: 15vw;
        margin: auto;
        margin-top: 5px;

        background-color: rgb(255, 232, 236);
    }

    .cardButton:hover {
        background-color: pink;
    }

    .buttons {
        margin-top: 5px;
    }

}
</style>