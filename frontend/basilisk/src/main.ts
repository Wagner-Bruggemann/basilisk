import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import PrimeVue from 'primevue/config'
import Aura from '@primeuix/themes/aura'

const primeVueTheme = {
  theme: {
    preset: Aura,
    options: {
      darkModeSelector: 'none',
      cssLayer: false,
    }
  },
}

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(PrimeVue, primeVueTheme)

app.mount('#app')
