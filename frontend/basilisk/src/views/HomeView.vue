<template>
  <main>
    
  </main>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useFuncionalityStore } from '@/state/services/FunctionalitysStore';

const functionalityStore = useFuncionalityStore();
const fetchAllFunctionalities = functionalities.fetchAllFunctionalities;
const functionalities = ref<Functionality>(null);

onMounted(() => {

})
</script>
