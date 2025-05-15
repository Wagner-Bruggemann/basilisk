<template>
    <div class="bg-white text-black p-10 border-2 border-gray-50 rounded-2xl w-full">
        Card
        <div class="flex">
            <div class="mx-10">
                <button class="border-gray rounded-md border-2 px-2 bg-blue-300"
                    @click="fetchAllProducts">
                    Find
                </button>
            </div>
            <div>
                {{ productStore.data }}
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useFuncionalityStore } from '@/state/services/FunctionalitysStore';

const functionalities = useFuncionalityStore();
const fetchAllFunctionalities = functionalities.fetchAllFunctionalities;

</script>