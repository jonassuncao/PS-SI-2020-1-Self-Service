import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AboutDetailComponent } from "./pages/about-detail/about-detail.component";
import { ProfileDetailComponent } from "./pages/profile-detail/profile-detail.component";
import { ServiceDetailComponent } from "./pages/service-detail/service-detail.component";

const routes: Routes = [
  { path: "", redirectTo: "profile" },
  { path: "profile", component: ProfileDetailComponent },
  { path: "services", component: ServiceDetailComponent },
  { path: "about", component: AboutDetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
