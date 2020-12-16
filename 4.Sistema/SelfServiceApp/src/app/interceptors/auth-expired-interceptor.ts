import {
  HttpErrorResponse,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { ToastController } from "@ionic/angular";
import { TranslateService } from "@ngx-translate/core";
import { throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { AuthService } from "../services/auth.service";

const STATUS_FORBIDDEN = 403;

@Injectable({
  providedIn: "root",
})
export class AuthExpiredInterceptor implements HttpInterceptor {
  constructor(
    private router: Router,
    private authService: AuthService,
    private toastController: ToastController,
    private translateService: TranslateService
  ) {}

  public intercept(req: HttpRequest<any>, next: HttpHandler) {
    return next.handle(req).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === STATUS_FORBIDDEN) {
            this.authService.clear();
            this.redirectToLogin();
            this.authExpired();
          }
        }
        return throwError(error);
      })
    );
  }

  private authExpired() {
    this.toastController
      .create({
        message: this.translateService.instant("global.messages.authExpired"),
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

  private redirectToLogin() {
    const url = this.router.routerState.snapshot.url;
    this.router.navigate(["login"], { queryParams: { redirect: url } });
  }
}
