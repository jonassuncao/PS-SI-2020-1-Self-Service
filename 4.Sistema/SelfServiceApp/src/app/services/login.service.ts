import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "./auth.service";
import { LoginCommand } from "../models/commands/login.command";

@Injectable()
export class LoginService {
  constructor(
    private authService: AuthService // private principalService: PrincipalService
  ) {}

  public login(credentials: LoginCommand): Observable<any> {
    return this.authService.authenticate(credentials);
    // .pipe(switchMap(() => this.principalService.identify()));
  }
}
