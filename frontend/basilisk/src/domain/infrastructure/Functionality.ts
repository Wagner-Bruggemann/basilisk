import type Field from '@/domain/infrastructure/Field'
import type { Operation } from './Operation'

export default interface Functionality {
  id: string
  name: string
  label: string
  fields: Field[]
  operations: Operation[]
}