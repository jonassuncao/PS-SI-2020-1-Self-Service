import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ApiService } from "./api.service";

@Injectable({
  providedIn: "root",
})
export class ClientService {
  private url = "/clients";

  constructor(private api: ApiService) {}

  public get(): Observable<any> {
    return this.api.get(this.url);
  }
}
