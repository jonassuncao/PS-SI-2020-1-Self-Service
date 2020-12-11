import { Observable, of } from "rxjs";
import { catchError, tap, map } from "rxjs/operators";

import { Injectable } from "@angular/core";
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  Router,
} from "@angular/router";

@Injectable()
export class LoginGuard implements CanActivate {
  constructor(private router: Router) {}

  public canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    const redirect = route.queryParams.redirect;
    return this.isUnauthenticated(redirect);
  }

  private isUnauthenticated(redirect: string): Observable<boolean> {
    // return this.principalService.isAuthenticated().pipe(
    //   map((authenticated) => !authenticated),
    //   tap((unauthenticated) => {
    //     if (!unauthenticated) {
    //       if (redirect) {
    //         this.router.navigateByUrl(redirect);
    //       } else {
    //         this.router.navigate(["home"]);
    //       }
    //     }
    //   }),
    //   catchError(() => of(true))
    // );
    return of(false);
  }
}
