import type { FieldType } from "./FieldType"
import type { FieldVariant } from "./FieldVariant"

export default interface Field {
  id: string
  name: string
  label: string
  type: FieldType
  variant: FieldVariant
  readonly: boolean
}