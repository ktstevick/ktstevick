import axios from 'axios';

const http = axios.create({
    baseURL: 'https://icanhazdadjoke.com/'
});

let header = {
    headers: {
        'Accept': 'text/plain'
    }
};

export default {
    getRandomJoke() {
        return http.get('', header);
    }
}