// import { concat, Observable, throwError } from "rxjs";
// import {
//   catchError,
//   delay,
//   map,
//   mergeMap,
//   retryWhen,
//   share,
//   take,
//   tap,
// } from "rxjs/operators";
// import { I18nService } from "./i18n.service";

// @Injectable({
//   providedIn: "root",
// })
// export class PrincipalService {
//   private account = new BehaviorSubject<Account>(null);

//   constructor(
//     private authService: AuthService,
//     private i18nService: I18nService,
//     private accountService: AccountService
//   ) {}

//   public hasAnyAuthority(authorities: string[]): Observable<boolean> {
//     return this.getAccount().pipe(
//       map((a) => (!!a ? this.hasAuths(a.authorities, authorities) : false))
//     );
//   }

//   public hasAuthority(authority: string): Observable<boolean> {
//     return this.getAccount().pipe(
//       map((account) => {
//         const auths = account.authorities;
//         return auths && auths.indexOf(authority) !== -1;
//       })
//     );
//   }

//   public identify(force: boolean = false): Observable<Account> {
//     if (force || !this.account.getValue()) {
//       if (this.authService.hasToken()) {
//         return this.load();
//       }
//     }
//     return this.getAccount();
//   }

//   public isAuthenticated(): Observable<boolean> {
//     return this.identify().pipe(map((account) => !!account));
//   }

//   public getAccount(): Observable<Account> {
//     return this.account.asObservable().pipe(share());
//   }

//   public get currentAccount(): Account {
//     return this.account.getValue();
//   }

//   public logout() {
//     this.authService.clear();
//     this.account.next(null);
//   }

//   private hasAuths(auths: string[], authorities: string[]): boolean {
//     if (auths && auths.length > 0) {
//       for (const authority of authorities) {
//         if (auths.indexOf(authority) !== -1) {
//           return true;
//         }
//       }
//     }
//   }

//   private load(): Observable<Account> {
//     return this.accountService.get().pipe(
//       tap((account) => this.loadAccount(account)),
//       retryWhen((errors) =>
//         errors.pipe(delay(2000), take(1), concat(this.onError()))
//       ),
//       catchError(() => this.onError()),
//       mergeMap(() => this.getAccount())
//     );
//   }

//   private loadAccount(account: Account) {
//     this.account.next(account);
//   }

//   private onError() {
//     return throwError("not authenticated");
//   }
// }
