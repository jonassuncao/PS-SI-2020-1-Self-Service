import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, throwError } from "rxjs";
import {
  catchError,
  concat,
  delay,
  map,
  mergeMap,
  retryWhen,
  share,
  take,
  tap,
} from "rxjs/operators";
import { AuthService } from "./auth.service";
import { ClientService } from "./client.service";
import { TranslateService } from "@ngx-translate/core";

@Injectable({
  providedIn: "root",
})
export class PrincipalService {
  private client = new BehaviorSubject<any>(null);

  constructor(
    private authService: AuthService,
    private clientService: ClientService,
    private translateService: TranslateService
  ) {}

  public identify(force: boolean = false): Observable<Account> {
    if (force || !this.client.getValue()) {
      if (this.authService.hasToken()) {
        return this.load();
      }
    }
    return this.getAccount();
  }

  public isAuthenticated(): Observable<boolean> {
    return this.identify().pipe(map((account) => !!account));
  }

  public getAccount(): Observable<Account> {
    return this.client.asObservable().pipe(share());
  }

  public get current(): Account {
    return this.client.getValue();
  }

  public logout() {
    this.authService.clear();
    this.client.next(null);
  }

  private load(): Observable<Account> {
    return this.clientService.get().pipe(
      tap(this.loadAccount),
      retryWhen((errors) =>
        errors.pipe(delay(2000), take(1), concat(this.onError()))
      ),
      catchError(() => this.onError()),
      mergeMap(() => this.getAccount())
    );
  }

  private loadAccount = (account: Account) => {
    this.client.next(account);
  };

  private onError(): Observable<never> {
    return throwError({
      error: {
        message: this.translateService.instant("global.error.notAuthenticated"),
      },
    });
  }
}
