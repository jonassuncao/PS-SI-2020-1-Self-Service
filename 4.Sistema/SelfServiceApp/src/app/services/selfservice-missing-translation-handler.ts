import {
  MissingTranslationHandler,
  MissingTranslationHandlerParams,
} from "@ngx-translate/core";

export class SelfServiceMissingTranslationHandler
  implements MissingTranslationHandler {
  public handle(params: MissingTranslationHandlerParams) {
    return params.key;
  }
}
