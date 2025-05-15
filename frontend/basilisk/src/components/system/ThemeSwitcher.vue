<template>
    <div class="flex justify-end mb-4">
      <label class="relative inline-flex items-center cursor-pointer">
        <input
          type="checkbox"
          v-model="isDarkMode"
          class="sr-only"
          @change="toggleTheme"
        />
        <div
          class="w-12 h-6 bg-gray-300 rounded-full shadow-inner transition-colors duration-300"
          :class="{ 'bg-blue-600': isDarkMode }"
        ></div>
        <div
          class="absolute w-4 h-4 bg-white rounded-full shadow top-1 left-1 transition-transform duration-300 transform"
          :class="{ 'translate-x-6': isDarkMode }"
        ></div>
      </label>
      <span class="ml-2 text-sm font-medium">
        {{ themeStore.isDarkMode ? 'Modo Escuro' : 'Modo Claro' }}
      </span>
    </div>
</template>

<script setup lang="ts">
    import { useThemeStore } from '@/state/system/Theme';

    const themeStore = useThemeStore();
    const isDarkMode = themeStore.isDarkMode;
    const toggleTheme = themeStore.toggleTheme;
</script>