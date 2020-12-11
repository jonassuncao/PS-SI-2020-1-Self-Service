import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { LayoutMainComponent } from "./pages/layout-main/layout-main.component";

@NgModule({
  declarations: [LayoutMainComponent],
  imports: [CommonModule, RouterModule],
})
export class LayoutModule {}
