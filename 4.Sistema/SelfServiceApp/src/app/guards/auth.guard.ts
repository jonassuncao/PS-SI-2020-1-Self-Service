import { Injectable } from "@angular/core";
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Route,
  Router,
  RouterStateSnapshot,
} from "@angular/router";
import { Observable, of } from "rxjs";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  public canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.isLogged(state.url);
  }

  public canActivateChild(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.canActivate(route, state);
  }

  public canLoad(route: Route): Observable<boolean> {
    return this.isLogged();
  }

  private isLogged(url?: string): Observable<boolean> {
    // return this.principalService.isAuthenticated().pipe(
    //   tap((authenticated) => {
    //     if (!authenticated) {
    //       this.navigateToLogin(url);
    //     }
    //   }),
    //   catchError(() => {
    //     this.navigateToLogin(url);
    //     return of(false);
    //   })
    // );
    this.navigateToLogin(url);
    return of(false);
  }

  private navigateToLogin(url: string) {
    this.router.navigate(["login"], { queryParams: { redirect: url } });
  }
}
