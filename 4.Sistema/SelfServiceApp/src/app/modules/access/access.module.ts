import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { AccessRoutingModule } from "./access-routing.module";
import { LayoutAccessComponent } from "./layout-access/layout-access.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";

@NgModule({
  declarations: [LoginComponent, LayoutAccessComponent, RegisterComponent],
  imports: [CommonModule, AccessRoutingModule],
})
export class AccessModule {}
