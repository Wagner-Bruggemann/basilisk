import axios from 'axios';
import { ref } from 'vue';
import { defineStore } from "pinia";

const api = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10_000,
    headers: {
        'Content-Type': 'application/json'
    }
});

export const useRestCommons = defineStore('restMethods', () => {

    const errorData = ref({
        path: '',
        message: ''
    });

    async function get<T>(path : string) : Promise<T | null> {
        return defaultMethodHandler<T>(path, api.get);
    }

    function post<T>(path: string, payload: any) {
        return defaultMethodHandler<T>(path, api.post, payload);
    }

    function put<T>(path: string, payload: any) {
        return defaultMethodHandler<T>(path, api.put, payload);
    }

    function del<T>(path: string) {
        return defaultMethodHandler<T>(path, api.delete);
    }

    async function defaultMethodHandler<T>(
        path: string,
        method: (path: string, ...args: any[]) => Promise<any>,
        payload?: any
      ): Promise<T | null> {
        try {
          const response = await (payload ? method(path, payload) : method(path));
          errorData.value = { path: '', message: '' };
          return response.data;
        } catch (error: any) {
          errorData.value = {
            path,
            message: error.response?.data?.message || error.message || 'Unknown error',
          };
          console.error(`Erro na requisição (${path}):`, errorData.value.message);
          return null;
        }
      }

    return { get, post, put, del, errorData };
});