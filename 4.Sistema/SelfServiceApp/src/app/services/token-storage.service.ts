import { Injectable } from "@angular/core";
import { Storage } from "@ionic/storage";

@Injectable({ providedIn: "root" })
export class TokenStorageService {
  private readonly key = "token";
  private value: string;

  constructor(private storage: Storage) {}

  public getValue(): string {
    if (!this.value) {
      this.retrieveValue();
    }
    return this.value;
  }

  public getBearerValue() {
    return `Bearer ${this.getValue()}`;
  }

  public save(token: string, rememberMe: boolean) {
    if (token) {
      this.storage.set(this.key, token);
    }
    this.value = token;
  }

  public clear() {
    this.storage.remove(this.key);
    this.value = null;
  }

  private async retrieveValue() {
    await this.storage.get(this.key).then((res) => (this.value = res));
  }
}
