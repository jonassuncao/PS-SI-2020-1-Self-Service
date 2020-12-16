import { AbstractControl } from "@angular/forms";
import { format, isDate } from "date-fns";
import { isBoolean, isNumber, isString } from "lodash";
import { DATE_REGEX } from "./regex";

export function toInteger(value: any): number {
  return parseInt(String(value), 10);
}

export function toString(value: any): string {
  return value !== undefined && value !== null ? `${value}` : "";
}

export function noBlank(value: any): boolean {
  return !!toString(value).length;
}

export function getValueInRange(value: number, max: number, min = 0): number {
  return Math.max(Math.min(value, max), min);
}

export function isInteger(value: any): value is number {
  return (
    typeof value === "number" && isFinite(value) && Math.floor(value) === value
  );
}

export function isDefined(value: any): boolean {
  return value !== undefined && value !== null;
}

export function padNumber(value: number) {
  if (isNumber(value)) {
    return `0${value}`.slice(-2);
  }
  return "";
}

export function regExpEscape(text: string) {
  return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
}

export function sanitize(text: string) {
  if (text) {
    return text.replace(/[^a-zA-Z0-9]/g, "");
  }
  return text;
}

export function sort(active: string, direction: "asc" | "desc" = "asc") {
  return { active, direction };
}

export function distribute(
  value: number,
  quantity: number,
  precision: number = 2
): number[] {
  const quota = Math.trunc((value / quantity) * 100) / 100;
  const result = [];
  for (let i = 0; i < quantity - 1; i++) {
    result.push(fixValue(quota));
  }
  const leftover = value - quota * quantity + quota;
  result.push(fixValue(leftover));
  return result;
}

export function fixValue(value: number, precision: number = 2): number {
  return Number(value.toFixed(precision));
}

export function calculateLeftover(
  quota: number,
  total: number,
  precision: number = 2
): number {
  const quotaTrunc = Number(quota.toFixed(precision)) * 100;
  const totalTrunc = Number(total.toFixed(precision)) * 100;
  return (
    Number(((totalTrunc % quotaTrunc) + quotaTrunc).toFixed(precision)) / 100
  );
}

export function convertDate(date: Date): string | null {
  return !date ? null : format(date, "YYYY-MM-DD");
}

export function isValidDate(date: any): boolean {
  if (isDate(date)) {
    return true;
  }
  if (isString(date) && DATE_REGEX.test(date)) {
    return !!Date.parse(date);
  }
  return false;
}

export function isValidBoolean(value: any): boolean {
  if (isBoolean(value)) {
    return true;
  }
  if (isString(value) && ["true", "false"].includes(value.toLowerCase())) {
    return true;
  }
  return false;
}

export function count(word: string, wanted: string): number {
  return word.split(wanted).length - 1;
}

export function shadeHexColor(color: string, percent: number) {
  const f = parseInt(color.slice(1), 16);
  const t = percent < 0 ? 0 : 255;
  const p = percent < 0 ? percent * -1 : percent;
  // tslint:disable-next-line: no-bitwise
  const R = f >> 16;
  // tslint:disable-next-line: no-bitwise
  const G = (f >> 8) & 0x00ff;
  // tslint:disable-next-line: no-bitwise
  const B = f & 0x0000ff;
  return (
    "#" +
    (
      0x1000000 +
      (Math.round((t - R) * p) + R) * 0x10000 +
      (Math.round((t - G) * p) + G) * 0x100 +
      (Math.round((t - B) * p) + B)
    )
      .toString(16)
      .slice(1)
  );
}

export function hasValue(obj: any): boolean {
  if (!obj) {
    return false;
  }
  return !!Object.keys(obj)
    .map((key) => obj[key])
    .find((v) => !!v);
}

export function hasValidator(
  control: AbstractControl,
  validator: string
): boolean {
  try {
    return (control.validator(control) || {}).hasOwnProperty(validator);
  } catch (e) {
    return false;
  }
}
