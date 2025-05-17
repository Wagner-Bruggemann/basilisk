import type Functionality from '@/domain/infrastructure/Functionality'
import { Operation } from '@/domain/infrastructure/Operation';

import { ref } from 'vue';
import { defineStore } from "pinia";

export const useFormStore = defineStore('formStore', () => {

    const form = ref<Functionality | null>(null);

    function initForm(domain : string) {
        form.value = {
            id: "asuoh-bnv641v641fbv-54rvbfbe41",
            name: "Product",
            label: "Produto",
            operations: [
                Operation.Create,
                Operation.Read,
                Operation.Update,
                Operation.Delete
            ],
            fields: [
                {
                    id: "fb6b51efb-54ebf64be-ebft65141",
                    name: "id",
                    label: "Identificador",
                    type: "string",
                    variant: null,
                    readonly: true
                },
                {
                    id: "fb6vvsefb-54ebf6wge-ebft65141",
                    name: "name",
                    label: "Nome",
                    type: "string",
                    variant: null,
                    readonly: false
                },
                {
                    id: "rbv6b51efb-54eb23454be-ebft65141",
                    name: "description",
                    label: "Descrição",
                    type: "string",
                    variant: "text-area",
                    readonly: false
                }
            ]
        }
    }

    return { form, initForm };
});