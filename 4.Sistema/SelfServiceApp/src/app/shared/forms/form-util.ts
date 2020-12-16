import {
  AbstractControl,
  FormArray,
  FormControl,
  FormGroup,
  ValidatorFn,
} from "@angular/forms";
import { countBy } from "lodash";
import { calculateLeftover } from "../utils/functions";

export function validateAllFormFields(formGroup: FormGroup) {
  if (formGroup) {
    formGroup.markAllAsTouched();
    if (formGroup.controls) {
      Object.keys(formGroup.controls).forEach((key) => {
        const control = formGroup.get(key);
        if (control instanceof FormControl) {
          control.markAsTouched();
        } else if (control instanceof FormGroup) {
          validateAllFormFields(control);
        } else if (control instanceof FormArray) {
          const array = control as FormArray;
          array.controls.forEach((c) => validateAllFormFields(c as FormGroup));
        }
      });
    }
  }
}

export function validateAllFormArrayFields(array: FormArray) {
  array.controls.forEach((c) => validateAllFormFields(c as FormGroup));
  array.markAllAsTouched();
}

export function resetForm(formGroup: FormGroup) {
  if (formGroup instanceof FormGroup) {
    reset(formGroup);
    Object.keys(formGroup.controls).forEach((key) =>
      resetControl(formGroup.get(key))
    );
  }
}

export function resetControl(control: AbstractControl) {
  reset(control);
  if (control instanceof FormGroup) {
    resetForm(control);
  } else if (control instanceof FormArray) {
    const array = control as FormArray;
    array.controls.forEach((c) => resetControl(c as FormGroup));
  }
}

function reset(control: AbstractControl) {
  control.reset();
  control.markAsPristine();
  control.markAsUntouched();
}

function validateField(formGroup: FormGroup, field: string) {
  const control = formGroup.get(field);
  if (control instanceof FormControl) {
    control.markAsTouched();
    control.setErrors({ required: true });
  } else if (control instanceof FormGroup) {
    this.validateField(control, field);
  }
}

export function validationError(formGroup: FormGroup, res: Response) {
  if (res && res.status === 400) {
    const param = res.json();
    if (param && param instanceof Array) {
      param.forEach((p) => validateField(formGroup, p.field));
    }
  }
}

export function maxPercentageValidator(value: number): ValidatorFn {
  return (control: AbstractControl) => {
    const invalid = value < control.value;
    return invalid ? { max: { max: value * 100 + "%" } } : null;
  };
}

export function hasAnyValueValidator(error: string, controls?: string[]) {
  return (form: FormGroup) => {
    const keys = !!controls ? controls : Object.keys(form.controls);
    const hasValue = !!keys.find(
      (key) => !!form.get(key).value && form.get(key).valid
    );
    keys.forEach((key) => loadError(form.get(key), !hasValue, error));
  };
}

export function uniqueIdValidator(
  arrayElement: string,
  idField: string,
  error: string
) {
  return (form: AbstractControl) => {
    const array = form.get(arrayElement) as FormArray;
    const count = countBy(array.controls.map((c) => c.get(idField).value));
    array.controls
      .map((c) => c.get(idField))
      .forEach((c) => loadError(c, count[c.value] > 1, error));
  };
}

export function updateValueAndValidity(
  control: AbstractControl,
  error: string
) {
  if (control.getError(error)) {
    control.updateValueAndValidity();
  }
}

export function loadError(
  control: AbstractControl,
  invalid: boolean,
  error: string,
  value: any = true
) {
  if (invalid) {
    control.setErrors({ ...control.errors, [error]: value });
  } else {
    updateValueAndValidity(control, error);
  }
}

export function percentagesValidator(
  elementArray: string,
  percentageField: string
) {
  return (form: AbstractControl) => {
    const percentages = form.get(elementArray) as FormArray;
    const controls = percentages.controls.map((c) => c.get(percentageField));
    const invalid =
      controls.map((c) => c.value).reduce((a, b) => a + b, 0) !== 1;
    controls.forEach((c) => loadError(c, invalid, "sumInvalidPercentage"));
  };
}

export function allocatePercentage(
  control: AbstractControl[],
  percentageField: string
) {
  const percentage = percentageByQuota(control);
  control.forEach((form) => form.get(percentageField).setValue(percentage));
  control[control.length - 1]
    .get(percentageField)
    .setValue(calculateLeftover(percentage, 1.0));
}

function percentageByQuota(control: AbstractControl[]) {
  const quantity = control.length || 1;
  return Math.trunc((1 / quantity) * 100) / 100;
}

export function disableControl(control: AbstractControl, disable: boolean) {
  if (control) {
    if (disable) {
      control.disable();
    } else {
      control.enable();
    }
  }
}
