<template>
  <div class="container mx-auto pa-4">
    <Card>
        <template #title>{{form?.label}}</template>
        <template #content>
            <Form
                class="flex flex-col gap-4 w-full sm:w-56" 
                v-slot="$form" 
                >
                <div 
                    class="flex flex-col gap-1" 
                    v-for="(field, index) in form?.fields as Array<Field>" 
                    :key="index"
                    >
                    <InputText 
                        v-if="field.variant === FieldVariant.Default" 
                        :name="field.name" 
                        :type="field.type" 
                        :placeholder="field.label" 
                        :readonly="field.readonly"
                        fluid
                        />
                    <TextArea 
                        v-if="field.variant === FieldVariant.TextArea" 
                        :name="field.name" 
                        :placeholder="field.label" 
                        :readonly="field.readonly"
                        fluid
                        />
                </div>
                <Button type="submit" severity="secondary" label="Submit" />
            </Form>
        </template>
    </Card>
  </div>
</template>

<script setup lang="ts">
import type Functionality from '@/domain/infrastructure/Functionality'
import type Field from '@/domain/infrastructure/Field'

import { ref, onMounted } from 'vue'
import { useFormStore } from '@/state/form/FormStore'

import Card from 'primevue/card'
import Button from 'primevue/button'
import InputText from 'primevue/inputText'
import TextArea from 'primevue/textArea'
import { Form } from '@primevue/forms'
import { FieldVariant } from '@/domain/infrastructure/FieldVariant'

const formStore = useFormStore()

const form = ref<Functionality | null>(formStore.form)

onMounted(() => {
    formStore.initForm("Product")
})

</script>
