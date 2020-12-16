import { Observable } from "rxjs";
import { Page } from "../models/page.model";
import { ApiService } from "./api.service";

export class ReadService<T> {
  constructor(protected api: ApiService, protected url: string) {}

  public getById(id: string): Observable<T> {
    return this.api.get<T>(`${this.url}/${id}`);
  }

  public existsById(id: string): Observable<boolean> {
    return this.api.get(`${this.url}/${id}/exists`);
  }

  public findById(id: string): Observable<T> {
    return this.api.get<T>(`${this.url}?id=${id}`);
  }

  public getAll(req?: any): Observable<T[]> {
    const params = this.api.pagerQueryService.params(req);
    return this.api.get<T[]>(`${this.url}`, params);
  }

  public query(req?: any): Observable<Page<T>> {
    return this.api.page<T>(this.url, req);
  }
}
