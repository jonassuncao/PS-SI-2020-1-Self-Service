import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { switchMap } from "rxjs/operators";
import { LoginCommand } from "../models/commands/login.command";
import { AuthService } from "./auth.service";
import { PrincipalService } from './principal.service';

@Injectable()
export class LoginService {
  constructor(
    private authService: AuthService,
    private principalService: PrincipalService
  ) {}

  public login(credentials: LoginCommand): Observable<any> {
    return this.authService
      .authenticate(credentials)
      .pipe(switchMap(() => this.principalService.identify()));
  }
}
