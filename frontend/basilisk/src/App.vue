<template>
  <div class="container mx-auto p-4">
    <ThemeSwitcher />
    <RouterView />
    <Button label="Botão" color="blue-100" />
  </div>
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router'
import ThemeSwitcher from './components/system/ThemeSwitcher.vue'
import Button from 'primevue/button'
</script>
