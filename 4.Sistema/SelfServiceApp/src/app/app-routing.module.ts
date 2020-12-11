import { NgModule } from "@angular/core";
import { PreloadAllModules, RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "./guards/auth.guard";
import { LayoutModule } from "./layouts/layout.module";

const routes: Routes = [
  // {
  //   path: "",
  //   // canActivate: [AuthGuard],
  //   component: LayoutModule,
  //   children: [
  //     {
  //       path: "",
  //       redirectTo: "home",
  //       pathMatch: "full",
  //     },
  //     {
  //       path: "home",
  //       loadChildren: () =>
  //         import("./modules/admin/home/home.module").then((m) => m.HomeModule),
  //     },
  //   ],
  // },
  {
    path: "",
    loadChildren: () =>
      import("./modules/access/access.module").then((m) => m.AccessModule),
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
