import { Component } from "@angular/core";
import { SplashScreen } from "@ionic-native/splash-screen/ngx";
import { StatusBar } from "@ionic-native/status-bar/ngx";
import { Platform } from "@ionic/angular";
import { BehaviorSubject } from "rxjs";
import { PrincipalService } from "../../../services/principal.service";

@Component({
  templateUrl: "./layout-main.component.html",
  styleUrls: ["./layout-main.component.scss"],
})
export class LayoutMainComponent {
  public selectedIndex = new BehaviorSubject(0);
  public user: any;
  public readonly appPages = [
    {
      title: "menu.profile",
      url: "home/profile",
      icon: "mail",
    },
    {
      title: "menu.services",
      url: "home/services",
      icon: "paper-plane",
    },
    {
      title: "menu.about",
      url: "home/about",
      icon: "heart",
    },
  ];

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private principalService: PrincipalService
  ) {
    this.initializeApp();
    this.principalService.getAccount().subscribe((res) => (this.user = res));
  }

  public initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }

  public onChangePage(page: number) {
    this.selectedIndex.next(page);
  }

  public isPage(page: number) {
    return page === this.selectedIndex.value;
  }

  public get folder(): string {
    return this.appPages[this.selectedIndex.value].title;
  }
}
