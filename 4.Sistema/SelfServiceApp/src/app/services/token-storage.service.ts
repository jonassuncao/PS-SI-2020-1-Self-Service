import { Injectable } from "@angular/core";
import { Storage } from "@ionic/storage";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({ providedIn: "root" })
export class TokenStorageService {
  private readonly key = "token";
  private value$ = new BehaviorSubject<string>(undefined);

  constructor(private storage: Storage) {
    this.storage.get(this.key).then((res) => this.value$.next(res));
  }

  public getValue(): Observable<string> {
    return this.value$.asObservable();
  }

  public value(): string {
    return this.value$.value;
  }

  public getBearerValue() {
    return `Bearer ${this.getValue()}`;
  }

  public save(token: string) {
    if (token) {
      this.storage.set(this.key, token);
    }
    this.value$.next(token);
  }

  public clear() {
    this.storage.remove(this.key);
    this.value$.next(null);
  }
}
