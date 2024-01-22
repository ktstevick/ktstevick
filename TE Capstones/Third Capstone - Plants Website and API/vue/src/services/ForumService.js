import axios from 'axios';

export default {
    getPostByForums(id) {
        return axios.get(`/posts/forum/${id}`)
    },
    getPostById(postid) {
        return axios.get(`/posts/${postid}`)
    },
    submitPost(newPost){
        return axios.post('/posts', newPost)
    }
    
}