import { ChangeDetectionStrategy, Component } from "@angular/core";
import { I18nService } from "./services/i18n.service";

@Component({
  selector: "app-root",
  templateUrl: "app.component.html",
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppComponent {
  constructor(private i18nService: I18nService) {}
}
