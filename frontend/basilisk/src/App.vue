<template>
  <div class="container mx-auto p-4">
    <ThemeSwitcher />
    <RouterView />
  </div>
</template>

<script setup lang="ts">
  import { RouterView } from 'vue-router';
  import ThemeSwitcher from './components/system/ThemeSwitcher.vue';
</script>
