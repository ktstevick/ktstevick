<template>
    <div id="wholeView">
        <div class="card" v-bind:class="[message.is_read ? 'read' : 'unread']">
            <div class="preview"> {{ message.message_body }}</div>
            <button @click="showReply = true">Reply</button>
            <button @click="toggleRead">{{ message.is_read ? 'Mark Unread' : 'Mark Read' }}</button>
        </div>
        <div v-if="showReply" id="replyForm">
            <textarea cols="60" rows="10" v-model="this.replyMessage.message_body"></textarea>
            <div>
                <button @click="submitReply" class="bottomButton">Submit</button>
                <button @click="cancelReply" class="bottomButton">Cancel</button>
            </div>
        </div>
    </div>
</template>

<script>

import router from '../router';
import MessageService from '../services/MessageService';

export default {

    data() {
        return {
            showReply: false,
            replyMessage: {
                to_user_id: this.message.from_user_id,
                from_user_id: this.message.to_user_id
            }
        }
    },
    props: {
        message: { type: Object, required: true }
    },
    methods: {
        onClick() {
            this.$router.push({ name: 'message', params: { id: this.message.message_id } });
            this.$store.commit('SET_ACTIVE_MESSAGE', this.message)
        },

        showReplyForm() {
            this.showReply = true
        },

        submitReply() {
            MessageService.postMessage(this.replyMessage)
            this.showReply = false,
                this.replyMessage = {
                    to_user_id: this.message.from_user_id,
                    from_user_id: this.message.to_user_id
                }
        },

        cancelReply() {
            this.showReply = false,
                this.replyMessage = {
                    to_user_id: this.message.from_user_id,
                    from_user_id: this.message.to_user_id
                }
        },

        toggleRead() {
            this.$store.commit('SET_ACTIVE_MESSAGE', this.message)
            this.$store.state.activeMessage.is_read = !this.message.is_read
            MessageService.updateMessage(this.$store.state.activeMessage)
            router.go();
        }
    }
}
</script>

<style scoped>

.card {
    text-align: center;
    border: 2px solid black;
    border-radius: 10px;
    width: 60vw;
    height: auto;
    margin: 20px;
    padding: 10px;
}

#replyForm {
    width: 40vw;
    display: grid;

    text-align: center;
    margin-left: auto;
    margin-right: auto;
}

.bottomButton {
    width: 15vw;
    height: 5vh;

    margin: 5px;
}

.card.read {
    background-color: white;
}

.card.unread {
    background-color: gray;
}

.sender {
    text-align: end;
}
</style>