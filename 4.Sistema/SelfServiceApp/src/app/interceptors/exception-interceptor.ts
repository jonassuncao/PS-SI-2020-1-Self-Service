import {
  HttpErrorResponse,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { ErrorService } from "../services/error.service";

const STATUS_COMMUNICATION_ERROR = [0, 504];
const STATUS_ERRORS = [400, 401, 500];

@Injectable({
  providedIn: "root",
})
export class ExceptionInterceptor implements HttpInterceptor {
  constructor(private errorService: ErrorService) {}

  public intercept(req: HttpRequest<any>, next: HttpHandler) {
    return next.handle(req).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse) {
          const res = error as HttpErrorResponse;
          if (STATUS_COMMUNICATION_ERROR.includes(res.status)) {
            this.errorService.show({ code: "error.communication" });
          }
          if (STATUS_ERRORS.includes(res.status)) {
            this.errorService.show(res.error);
          }
        }
        return throwError(error);
      })
    );
  }
}
