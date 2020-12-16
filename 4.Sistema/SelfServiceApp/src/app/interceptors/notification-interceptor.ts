import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ToastController } from "@ionic/angular";
import { TranslateService } from "@ngx-translate/core";
import { tap } from "rxjs/operators";

const STATUS_SUCCESS = [200, 201, 202];

@Injectable({
  providedIn: "root",
})
export class NotificationInterceptor implements HttpInterceptor {
  constructor(
    private toastController: ToastController,
    private translateService: TranslateService
  ) {}

  public intercept(req: HttpRequest<any>, next: HttpHandler) {
    return next.handle(req).pipe(
      tap((event) => {
        if (event instanceof HttpResponse) {
          const res = event as HttpResponse<any>;
          const alertKey = res.headers.get("x-alert");
          if (alertKey) {
            const alertParam = res.headers.get("x-params") || null;
            this.alert(res.status, alertKey, alertParam);
          }
        }
      })
    );
  }

  private alert(status: number, alertKey: string, param: string) {
    if (STATUS_SUCCESS.indexOf(status) >= 0) {
      this.translateService
        .get(alertKey, { param })
        .subscribe((res) => this.showSuccess(res));
    }
  }

  private showSuccess(message: any) {
    this.toastController
      .create({
        message,
        color: "success",
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
