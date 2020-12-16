import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "../services/auth.service";

const Authorization = "Authorization";
const ApplicationJson = "application/json";
const IGNORE_AUTH_PATHS = ["/api/authentication"];

@Injectable({
  providedIn: "root",
})
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  public intercept(req: HttpRequest<any>, next: HttpHandler) {
    const headersConfig = { Accept: ApplicationJson };
    const token = this.authService.getToken();
    if (token && !this.isIgnored(req.url)) {
      headersConfig[Authorization] = `Bearer ${token}`;
    }
    const request = req.clone({ setHeaders: headersConfig });
    return next.handle(request);
  }

  private isIgnored(url: string) {
    return IGNORE_AUTH_PATHS.includes(url);
  }
}
