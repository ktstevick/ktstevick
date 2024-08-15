import { createApp } from 'vue'
import { createStore } from './store'
import App from './App.vue'
import router from './router'

const app = createApp(App);
const store = createStore();

app.use(router);
app.use(store);
app.mount('#app');
