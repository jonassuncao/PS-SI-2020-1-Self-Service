import { registerLocaleData } from "@angular/common";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import localePt from "@angular/common/locales/pt";
import { LOCALE_ID, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { RouteReuseStrategy } from "@angular/router";
import { SplashScreen } from "@ionic-native/splash-screen/ngx";
import { StatusBar } from "@ionic-native/status-bar/ngx";
import { IonicModule, IonicRouteStrategy } from "@ionic/angular";
import { IonicStorageModule } from "@ionic/storage";
import {
  MissingTranslationHandler,
  TranslateLoader,
  TranslateModule,
  TranslateService,
} from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { AuthGuard } from "./guards/auth.guard";
import { httpInterceptorProviders } from "./interceptors";
import { SelfServiceServiceModule } from "./services/self-service-service.module";
import { SelfServiceMissingTranslationHandler } from "./services/selfservice-missing-translation-handler";
import { SharedModule } from "./shared/shared.module";
import { LoginGuard } from "src/app/guards/login.guard";
import { LayoutMainComponent } from "./layouts/pages/layout-main/layout-main.component";

registerLocaleData(localePt);
@NgModule({
  declarations: [AppComponent, LayoutMainComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    IonicModule.forRoot(),
    IonicStorageModule.forRoot(),
    SharedModule,
    AppRoutingModule,
    SelfServiceServiceModule,
    HttpClientModule,
    TranslateModule.forRoot({
      useDefaultLang: true,
      missingTranslationHandler: {
        provide: MissingTranslationHandler,
        useClass: SelfServiceMissingTranslationHandler,
      },
      loader: {
        provide: TranslateLoader,
        useFactory: (http: HttpClient) =>
          new TranslateHttpLoader(
            http,
            "assets/i18n/",
            ".json?cb" + new Date().getTime()
          ),
        deps: [HttpClient],
      },
    }),
  ],
  providers: [
    AuthGuard,
    LoginGuard,
    StatusBar,
    SplashScreen,
    httpInterceptorProviders,
    {
      provide: LOCALE_ID,
      useFactory: (translate: TranslateService) =>
        translate.currentLang || "pt-br",
      deps: [TranslateService],
    },
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
