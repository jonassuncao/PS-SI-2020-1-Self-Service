import { NgModule, Optional, SkipSelf } from "@angular/core";
import { ApiService } from "./api.service";
import { AuthService } from "./auth.service";
import { ErrorService } from "./error.service";
import { I18nService } from "./i18n.service";
import { LoginService } from "./login.service";
import { PagerQueryService } from "./pager-query.service";
import { PrincipalService } from "./principal.service";
import { TokenStorageService } from "./token-storage.service";
import { ValidationTranslateService } from "./validation-translate.service";

@NgModule({
  providers: [
    I18nService,
    LoginService,
    PagerQueryService,
    ValidationTranslateService,
    PrincipalService,
    LoginService,
    AuthService,
    ApiService,
    ErrorService,
    TokenStorageService,
  ],
})
export class SelfServiceServiceModule {
  constructor(@Optional() @SkipSelf() parentModule: SelfServiceServiceModule) {
    if (parentModule) {
      throw new Error(
        "SelfServiceServiceModule has already been loaded. Import SelfServiceServiceModule in the AppModule only."
      );
    }
  }
}
