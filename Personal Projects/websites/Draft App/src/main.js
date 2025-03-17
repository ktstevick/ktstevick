import './assets/main.css'

import { createApp } from 'vue'
import { createStore } from './store'
import App from './App.vue'
import router from './router'

const store = createStore();

const app = createApp(App)
app.use(router)
app.use(store)
app.mount('#app')
