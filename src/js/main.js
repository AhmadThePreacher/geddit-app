import { createApp } from 'vue'
import App from '/components/App.vue'
import router from '/router'

// Import our custom CSS
import '/scss/styles.scss'
import '/assets/styles.css'

if (!localStorage.getItem("pages")) {
    localStorage.setItem("pages", JSON.stringify([]));
}

if (!localStorage.getItem("subreddits")) {
    localStorage.setItem("subreddits", JSON.stringify([]));
}

if (!localStorage.getItem("hidden_posts")) {
    localStorage.setItem("hidden_posts", JSON.stringify([]));
}

if (!localStorage.getItem("autoplay")) {
    localStorage.setItem("autoplay", JSON.stringify(true));
}

if (!localStorage.getItem("title_size")) {
    localStorage.setItem("title_size", JSON.stringify('small'));
}

createApp(App).use(router).mount('#app');