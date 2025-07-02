/* eslint-disable import/order */
import '@/@iconify/icons-bundle'
import App from '@/App.vue'
import vuetify from '@/plugins/vuetify'
import { loadFonts } from '@/plugins/webfontloader'
import router from '@/router'
import '@/styles/styles.scss'
import '@core/scss/index.scss'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import axios from 'axios';
import { Icon } from '@iconify/vue';

loadFonts()

// Create vue app
const app = createApp(App)

// Setting Config
// const isCodespace = window.location.hostname.includes('github.dev');
// axios.defaults.baseURL = isCodespace ? 'https://' + window.location.hostname.replace('8080','8088') : 'http://localhost:8088';
// axios.defaults.baseURL = 'https://symmetrical-sniffle-vxppj9qx79pfjr5-8088.app.github.dev';
axios.defaults.baseURL = '';
axios.defaults.withCredentials = true;
app.config.globalProperties.$axios = axios;

// CORS 요청 헤더 설정
axios.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json';
    return config;
})
// Component
app.component('Icon',Icon)

// Use plugins
app.use(vuetify)
app.use(createPinia())
app.use(router)

// Mount vue app
app.mount('#app')
