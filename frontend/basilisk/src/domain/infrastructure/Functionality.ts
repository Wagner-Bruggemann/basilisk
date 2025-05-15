export interface Functionality {
    id: string,
    name: string,
    fields: Field[]
}

export interface Field {
    id: string,
    name: string,
    type: string,
}