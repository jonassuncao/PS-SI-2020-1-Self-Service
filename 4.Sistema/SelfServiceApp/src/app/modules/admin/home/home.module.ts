import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { HomeRoutingModule } from "./home-routing.module";
import { AboutDetailComponent } from "./pages/about-detail/about-detail.component";
import { ProfileDetailComponent } from "./pages/profile-detail/profile-detail.component";
import { ServiceDetailComponent } from "./pages/service-detail/service-detail.component";

@NgModule({
  declarations: [
    ServiceDetailComponent,
    ProfileDetailComponent,
    AboutDetailComponent,
  ],
  imports: [CommonModule, HomeRoutingModule],
})
export class HomeModule {}
