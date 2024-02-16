import axios from 'axios';

const http = axios.create({
    baseURL: 'https://icanhazdadjoke.com/'
});

// Just makes sure the data is returned to us in an easily used format
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