import {
  formatCurrency,
  formatDate,
  formatPercent,
  getCurrencySymbol,
  getLocaleNumberSymbol,
  getNumberOfCurrencyDigits,
  NumberSymbol,
  formatNumber,
} from "@angular/common";
import { Injectable } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";

@Injectable({
  providedIn: "root",
})
export class I18nService {
  private defaultI18nLang = "pt-br";

  constructor(private translateService: TranslateService) {
    this.translateService.setDefaultLang(this.defaultI18nLang);
    this.change(this.defaultI18nLang);
  }

  public config() {
    this.change(this.defaultI18nLang);
  }

  public change(locale: string) {
    this.translateService.use(locale);
  }

  private get currencySymbol(): string {
    return getCurrencySymbol(this.currencyCode, "wide", this.locale);
  }

  private get currencyCode(): string {
    return "BRL";
  }

  public get currencyDefaultPrecision(): number {
    return getNumberOfCurrencyDigits(this.currencyCode);
  }
  public get symbolNegative(): string {
    return getLocaleNumberSymbol(this.locale, NumberSymbol.MinusSign);
  }

  public get symbolCurrencyGroup(): string {
    return getLocaleNumberSymbol(this.locale, NumberSymbol.CurrencyGroup);
  }

  public get symbolCurrencyDecimal(): string {
    return getLocaleNumberSymbol(this.locale, NumberSymbol.CurrencyDecimal);
  }

  public currency(value: number, digitsInfo?: string) {
    return formatCurrency(
      value,
      this.locale,
      this.currencySymbol,
      this.currencyCode,
      digitsInfo
    );
  }

  public formatNumber(value: number, digitsInfo?: string) {
    return formatNumber(value, this.locale, digitsInfo);
  }

  public percent(value: number, digitsInfo?: string) {
    return formatPercent(value, this.locale, digitsInfo);
  }

  public date(value: string | number | Date, format: string): string {
    return formatDate(value, format, this.locale);
  }

  private get locale() {
    return this.translateService.currentLang;
  }
}
