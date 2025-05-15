import { ref } from 'vue';
import { defineStore } from "pinia";
import { useRestCommons } from '@/state/services/requests/RestCommons'

interface Functionality {
    id: string,
    name: string,
    type: string,
    disabled: string
}

export const useFuncionalityStore = defineStore('funcionalityStore', () => {
    const data = ref<Functionality[] | null>(null);
    const requests = useRestCommons();

    async function fetchAllFunctionalities() {
        data.value = await requests.get("/v1/infrastructure/functionalities");
    }

    return { data, fetchAllFunctionalities }
}) 