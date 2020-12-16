import { AbstractControl } from '@angular/forms';

export type FormModel<T> = {
  [P in keyof T]: AbstractControl | T[P] | [T[P] | { value: T[P]; disabled: boolean }, any?];
};
