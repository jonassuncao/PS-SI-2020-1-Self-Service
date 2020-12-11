import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeDetailComponent } from "./pages/home-detail.component";

const routes: Routes = [
  {
    path: "",
    children: [{ path: "", component: HomeDetailComponent }],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
