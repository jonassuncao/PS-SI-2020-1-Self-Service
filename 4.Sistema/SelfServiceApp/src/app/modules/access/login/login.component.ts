import {
  ChangeDetectionStrategy,
  Component,
  OnDestroy,
  OnInit,
} from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
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
  private redirect: string;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private activatedRoute: ActivatedRoute,
    private tokenStorageService: TokenStorageService,
    private validationTranslateService: ValidationTranslateService
  ) {
    this.redirect = this.activatedRoute.snapshot.queryParams.redirect;
  }

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
      username: [undefined, Validators.required],
      password: [undefined, [Validators.required, Validators.minLength(5)]],
    };
  }

  private login(value: any) {
    this.tokenStorageService.clear();
    this.loginService
      .login(value)
      .pipe(finalize(() => this.submitted$.next(false)))
      .subscribe(this.redirectTo);
  }

  private redirectTo = () => {
    if (this.redirect) {
      this.router.navigateByUrl(this.redirect);
    } else {
      this.router.navigate(["home"]);
    }
  };
}
