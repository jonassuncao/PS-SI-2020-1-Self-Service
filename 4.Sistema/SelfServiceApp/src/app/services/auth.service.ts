import { Injectable } from "@angular/core";
import { JwtHelperService } from "@auth0/angular-jwt";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { LoginCommand } from "../models/commands/login.command";
import { ApiService } from "./api.service";
import { TokenStorageService } from "./token-storage.service";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private token: string;
  private helper = new JwtHelperService();

  constructor(
    private api: ApiService,
    private tokenStorageService: TokenStorageService
  ) {
    this.loadToken(this.tokenStorageService.getValue());
  }

  public authenticate(command: LoginCommand): Observable<string> {
    return this.api
      .post("/authentication", command)
      .pipe(map(this.extractAndLoadToken));
  }

  public getToken() {
    return this.token;
  }

  public hasToken(): boolean {
    return !!this.token;
  }

  public getTokenValue(): { sub: string; auth: string[]; tnt: string } {
    return this.helper.decodeToken(this.token);
  }

  public clear() {
    this.token = null;
    this.tokenStorageService.clear();
  }

  private extractAndLoadToken = (res: any): string => {
    const token = res.id_token;
    this.tokenStorageService.save(token, false);
    this.loadToken(token);
    return token;
  };

  private loadToken(token: string) {
    this.token = token;
  }
}
