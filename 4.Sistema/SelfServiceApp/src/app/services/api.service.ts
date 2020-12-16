import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map, take } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { Page } from "../models/page.model";
import { PagerQueryService } from "./pager-query.service";

@Injectable({
  providedIn: "root",
})
export class ApiService {
  public readonly baseUrl = environment.SERVER_API_URL;

  constructor(
    public readonly http: HttpClient,
    public readonly pagerQueryService: PagerQueryService
  ) {}

  public get<T>(
    url: string,
    params: HttpParams = new HttpParams()
  ): Observable<T> {
    return this.http
      .get<T>(`${this.baseUrl}${url}`, { params })
      .pipe(take(1));
  }

  public getString(
    url: string,
    params: HttpParams = new HttpParams()
  ): Observable<string> {
    return this.http
      .get<string>(`${this.baseUrl}${url}`, {
        params,
        responseType: "text" as "json",
      })
      .pipe(take(1));
  }

  public page<T>(url: string, req?: any | HttpParams): Observable<Page<T>> {
    const params = this.resolveParams(req);
    return this.http
      .get<T>(`${this.baseUrl}${url}`, { params, observe: "response" })
      .pipe(map(this.pagerQueryService.response))
      .pipe(take(1));
  }

  public put<T>(
    path: string,
    body: any = null,
    params: HttpParams = new HttpParams()
  ): Observable<T> {
    return this.http
      .put<T>(`${this.baseUrl}${path}`, body, { params })
      .pipe(take(1));
  }

  public patch<T>(
    path: string,
    body: any = {},
    params: HttpParams = new HttpParams()
  ): Observable<T> {
    return this.http
      .patch<T>(`${this.baseUrl}${path}`, body, { params })
      .pipe(take(1));
  }

  public post<T>(
    path: string,
    body: any = {},
    params: HttpParams = new HttpParams()
  ): Observable<T> {
    return this.http
      .post<T>(`${this.baseUrl}${path}`, body, { params })
      .pipe(take(1));
  }

  public delete<T>(
    path: string,
    params: HttpParams = new HttpParams()
  ): Observable<T> {
    return this.http
      .delete<T>(`${this.baseUrl}${path}`, { params })
      .pipe(take(1));
  }

  private resolveParams(req: any): HttpParams {
    if (req instanceof HttpParams) {
      return req;
    }
    return this.pagerQueryService.params(req);
  }
}
