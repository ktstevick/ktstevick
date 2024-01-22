import axios from "axios";

export default {

    getUserMessages(id) {
        return axios.get(`/messages/user/${id}`)
    },

    getMessageById(id) {
        return axios.get(`/messages/${id}`)
    },

    postMessage(newPost){
        return axios.post('/messages', newPost)
    },

    updateMessage(updatePost){
        return axios.put(`/messages/${updatePost.message_id}`,updatePost)
    }



}