import { Injectable } from "@angular/core";
import { ToastController } from "@ionic/angular";
import { TranslateService } from "@ngx-translate/core";

@Injectable({
  providedIn: "root",
})
export class ErrorService {
  constructor(
    private toastController: ToastController,
    private translateService: TranslateService
  ) {}

  public show(error: any) {
    switch (error.code) {
      case "error.communication":
        this.communcationError();
        break;
      case "error.authentication":
        this.authenticationError(error);
        break;
      case "error.accessDenied":
      case "error.internalServerError":
        this.internalError(error);
        break;
      case "error.invalidParam":
      case "error.invalidOperation":
        this.invalidOperation(error);
        break;
      case "error.multiValidation":
        this.multiValidation(error);
        break;
      case "error.validation":
        this.validation(error);
        break;
      default:
        this.unexpectedError();
        break;
    }
  }

  private unexpectedError() {
    this.translateService.get("error.unexpected").subscribe((value) => {
      this.showError(value);
    });
  }

  private validation(error: any) {
    if (error.param) {
      const body = error.param
        .map((p: any) => p.field + ": " + p.message)
        .join("<br>");
      this.showError(error.message, body);
    } else {
      this.showError(error.message);
    }
  }

  private multiValidation(error: any) {
    if (error.param) {
      const messages = error.param as string[];
      this.showError(error.message, messages.join("<br>"));
    } else {
      this.showError(error.message);
    }
  }

  private invalidOperation(error: any) {
    this.showError(error.message);
  }

  private authenticationError(error: any) {
    this.translateService.get("error.authentication").subscribe((value) => {
      this.showError(value, error.message);
    });
  }

  private communcationError() {
    this.translateService
      .get(["error.communication", "error.checkConnection"])
      .subscribe((res) => {
        this.showError(
          res["error.communication"],
          res["error.checkConnection"]
        );
      });
  }

  private internalError(error: any) {
    this.showError(error.message);
  }

  private showError(header: any, message?: any) {
    this.toastController
      .create({
        header,
        message,
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
}
