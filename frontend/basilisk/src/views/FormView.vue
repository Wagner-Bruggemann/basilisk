<template>
  <div class="container mx-auto pa-4">
    <Card>
        <template #title>{{form?.label}}</template>
        <template #content>
            <div v-for="(field, index) in form?.fields as Array<Field>" :key="index">
                {{ field.label }}
            </div>
        </template>
    </Card>
  </div>
</template>

<script setup lang="ts">
import type Functionality from '@/domain/infrastructure/Functionality'
import type Field from '@/domain/infrastructure/Field'

import { ref } from 'vue'
import { useFormStore } from '@/state/form/FormStore'

import Card from 'primevue/card'

const formStore = useFormStore()

const form = ref<Functionality | null>(formStore.form)
const initForm = formStore.initForm("Product")

</script>
