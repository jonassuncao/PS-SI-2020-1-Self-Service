import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginGuard } from "src/app/guards/login.guard";
import { LayoutAccessComponent } from "./layout-access/layout-access.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";

const routes: Routes = [
  {
    path: "",
    component: LayoutAccessComponent,
    children: [
      { path: "", redirectTo: "login" },
      {
        path: "login",
        canActivate: [LoginGuard],
        component: LoginComponent,
      },
      { path: "register", component: RegisterComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AccessRoutingModule {}
