import { NgModule, Optional, SkipSelf } from "@angular/core";
import { I18nService } from "./i18n.service";

@NgModule({
  providers: [I18nService],
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
