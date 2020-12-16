import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { IonicModule } from "@ionic/angular";
import { TranslateModule } from "@ngx-translate/core";
import { ReactiveFormsModule } from "@angular/forms";
import { LabelErrorComponent } from "./label-error/label-error.component";

@NgModule({
  declarations: [LabelErrorComponent],
  imports: [
    CommonModule,
    IonicModule,
    TranslateModule.forChild(),
    ReactiveFormsModule,
  ],
  exports: [
    IonicModule,
    TranslateModule,
    ReactiveFormsModule,
    LabelErrorComponent,
  ],
})
export class SharedModule {}
