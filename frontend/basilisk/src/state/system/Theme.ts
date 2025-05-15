import { defineStore } from 'pinia';
import { ref, onMounted } from 'vue';

export const useThemeStore = defineStore('theme', () => {
    const isDarkMode = ref(false);

    function toggleTheme() {
        isDarkMode.value = !isDarkMode.value;
        const theme = isDarkMode.value ? 'dark' : 'light';
        document.documentElement.setAttribute('data-theme', theme);
        localStorage.setItem('theme', theme);
    }

    onMounted(() => {
        const savedTheme = localStorage.getItem('theme') || 'light';
        isDarkMode.value = savedTheme === 'dark';
        document.documentElement.setAttribute('data-theme', savedTheme);
    });

    return {isDarkMode, toggleTheme}
}) 