import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { ToastController } from "@ionic/angular";
import { TranslateService } from "@ngx-translate/core";
import { ErrorMessages } from "../shared/forms";

@Injectable({
  providedIn: "root",
})
export class ValidationTranslateService {
  constructor(
    private translateService: TranslateService,
    private toastController: ToastController
  ) {}

  public errors(
    form: FormGroup | FormArray,
    path: string = "global.error"
  ): ErrorMessages {
    return new ErrorMessages(this.translateService, form, path);
  }

  public valid(form: FormGroup) {
    form.markAllAsTouched();
    this.toastController
      .create({
        message: this.translate("global.error.invalidFields"),
        color: "danger",
        buttons: [
          {
            icon: "close",
            role: "cancel",
          },
        ],
        duration: 2000,
      })
      .then((res) => res.present());
  }

  private translate(key: string): string {
    return this.translateService.instant(key);
  }
}
