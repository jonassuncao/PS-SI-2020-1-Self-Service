import { AbstractControl, FormArray, FormGroup } from "@angular/forms";
import { TranslateService } from "@ngx-translate/core";

export class ErrorMessages {
  constructor(
    private translateService: TranslateService,
    private form: FormGroup | FormArray,
    private path: string
  ) {}

  public get(field?: string): string[] {
    return this.getAbsolute(this.path, field);
  }

  public getAbsolute(path: string, field?: string): string[] {
    if (field) {
      const control = this.control(field);
      return this.erros(control, path, field);
    }
    return this.erros(this.form, path);
  }

  private erros(
    control: AbstractControl,
    path: string,
    field?: string
  ): string[] {
    if (control && control.touched && control.invalid && control.errors) {
      return Object.keys(control.errors)
        .slice(0, 1)
        .map((errorKey) => {
          const errorPath = this.pathError(path, field, errorKey);
          return this.translateService.instant(
            errorPath,
            control.getError(errorKey)
          );
        });
    }
    return [];
  }

  private control(path: string): AbstractControl {
    let control = null;
    path.split(".").forEach((key) => {
      if (control) {
        control = control.controls[key];
      } else {
        control = this.form.controls[key];
      }
    });
    return control;
  }

  private pathError(path: string, field: string, errorKey: string): string {
    if (path.indexOf("global") >= 0) {
      return `${path}.${errorKey}`;
    }
    const paths = [path];
    if (field) {
      paths.push(field);
    }
    paths.push(errorKey);
    return paths.join(".");
  }
}
