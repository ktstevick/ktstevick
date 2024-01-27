import './assets/main.css'


import { createApp } from 'vue'
import App from './App.vue'
import { createStore } from './store';

// Constants
const store = createStore();
const app = createApp(App);

// Add the store to the app
app.use(store)
app.mount('#app') // Mounting meaning, load it into the DOM