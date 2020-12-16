import { HttpParams, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { isArray, isBoolean, isDate, isNil, isNumber, isString } from "lodash";
import { Page } from "../models/page.model";

export const TOTAL_COUNT = "X-Total-Count";

@Injectable({
  providedIn: "root",
})
export class PagerQueryService {
  public response<T>(res: HttpResponse<T>): Page<T> {
    const total = Number(res.headers.get(TOTAL_COUNT));
    const itens = (res.body as unknown) as T[];
    return { items: itens, total };
  }

  public params(req: any): HttpParams {
    let params = new HttpParams();
    this.load(req).forEach((value, key) => (params = params.set(key, value)));
    return params;
  }

  public paramsSort(req: any): HttpParams {
    let params = new HttpParams();
    if (req) {
      this.sort(req).forEach((value, key) => (params = params.set(key, value)));
      if (req.query) {
        params = params.set("q", req.query);
      }
    }
    return params;
  }

  public load(req: any): Map<string, string> {
    let params = new Map<string, string>();
    if (req) {
      this.extra(req).forEach(
        (value, key) => (params = params.set(key, value))
      );
      this.paginator(req).forEach(
        (value, key) => (params = params.set(key, value))
      );
      this.sort(req).forEach((value, key) => (params = params.set(key, value)));
    }
    return params;
  }

  private sort(req: any): Map<string, string> {
    const params = new Map<string, string>();
    if (req && req.sort) {
      const active = req.sort.active;
      if (!!active) {
        let activeSort = active;
        const direction = req.sort.direction;
        if (direction) {
          activeSort = `${active},${direction}`;
        }
        params.set("sort", activeSort);
      }
    }
    return params;
  }

  private extra(req: any): Map<string, string> {
    const params = new Map<string, string>();
    if (req) {
      Object.keys(req).forEach((key) => {
        const value = this.convertExtraValue(req[key]);
        if (!isNil(value)) {
          params.set(key, value);
        }
      });
    }
    return params;
  }

  private convertExtraValue(value: any): string {
    if (isNumber(value)) {
      return String(value);
    } else if (isBoolean(value)) {
      return String(value);
    } else if (isString(value) && value) {
      return value;
    } else if (isDate(value)) {
      return value.toISOString();
    } else if (isArray(value) && value.length > 0) {
      return value.join(",");
    }
  }

  private paginator(req: any): Map<string, string> {
    const params = new Map<string, string>();
    const page = this.pageIndex(req);
    if (page) {
      params.set("page", String(page));
    }
    const size = this.pageSize(req);
    if (size) {
      params.set("size", String(size));
    }
    return params;
  }

  private pageIndex(req: any): number {
    if (req) {
      if (req.paginator) {
        return req.paginator.pageIndex;
      }
      return req.page;
    }
    return null;
  }

  private pageSize(req: any): number {
    if (req) {
      if (req.paginator) {
        return req.paginator.pageSize;
      }
      return req.size;
    }
    return null;
  }
}
