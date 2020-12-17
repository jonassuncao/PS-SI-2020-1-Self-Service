import { Injectable } from "@angular/core";
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from "@angular/router";
import { Observable, of } from "rxjs";
import { catchError, map, tap } from "rxjs/operators";
import { PrincipalService } from "../services/principal.service";

@Injectable()
export class LoginGuard implements CanActivate {
  constructor(
    private router: Router,
    private principalService: PrincipalService
  ) {}

  public canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    const redirect = route.queryParams.redirect;
    return this.isUnauthenticated(redirect);
  }

  private isUnauthenticated(redirect: string): Observable<boolean> {
    return this.principalService.isAuthenticated().pipe(
      map((authenticated) => !authenticated),
      tap((unauthenticated) => {
        if (!unauthenticated) {
          if (redirect) {
            this.router.navigateByUrl(redirect);
          } else {
            this.router.navigate(["home"]);
          }
        }
      }),
      catchError(() => of(true))
    );
  }
}
