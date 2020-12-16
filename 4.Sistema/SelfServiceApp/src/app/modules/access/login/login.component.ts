import {
  ChangeDetectionStrategy,
  Component,
  OnDestroy,
  OnInit,
} from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { BehaviorSubject } from "rxjs";
import { finalize } from "rxjs/operators";
import { LoginCommand } from "src/app/models/commands/login.command";
import { LoginService } from "src/app/services/login.service";
import { TokenStorageService } from "src/app/services/token-storage.service";
import { ValidationTranslateService } from "src/app/services/validation-translate.service";
import { ErrorMessages } from "src/app/shared/forms";
import { FormModel } from "../../../shared/forms/form-model";

@Component({
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginComponent implements OnInit, OnDestroy {
  public form: FormGroup;
  public errors: ErrorMessages;
  public submitted$ = new BehaviorSubject(false);

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private tokenStorageService: TokenStorageService,
    private validationTranslateService: ValidationTranslateService
  ) {}

  public ngOnInit() {
    this.initForm();
  }

  public ngOnDestroy(): void {
    this.submitted$.complete();
  }

  public onSubmit(form: FormGroup) {
    if (form.valid) {
      if (!this.submitted$.value) {
        this.submitted$.next(true);
        this.login(form.value);
      }
    } else {
      this.validationTranslateService.valid(form);
    }
  }

  private initForm() {
    this.form = this.formBuilder.group(this.formModel);
    this.errors = this.validationTranslateService.errors(this.form);
  }

  private get formModel(): FormModel<LoginCommand> {
    return {
      username: ["fornecedor@email.com", Validators.required],
      password: ["@email.com", [Validators.required, Validators.minLength(5)]],
    };
  }

  private login(value: any) {
    this.tokenStorageService.clear();
    this.loginService
      .login(value)
      .pipe(finalize(() => this.submitted$.next(false)))
      .subscribe(this.redirectToHome);
  }

  private redirectToHome = () => {
    console.log("redirecionar");
  };
}
