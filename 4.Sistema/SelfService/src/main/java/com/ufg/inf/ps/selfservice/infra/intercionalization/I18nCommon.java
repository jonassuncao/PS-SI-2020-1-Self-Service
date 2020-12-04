package com.ufg.inf.ps.selfservice.infra.intercionalization;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
public enum I18nCommon implements I18nKey {
  // System
  ERROR_INTERNAL("error.internal"),
  INVALID_OPERATION("invalid.operation"),
  ERROR_UNAUTHORIZED("error.unauthorized"),
  TOKEN_EXPIRED("token.expired"),
  TOKEN_INVALID("token.invalid"),
  // HTTP
  HTTP_OK("http.ok"),
  ;

  private final String key;

  private I18nCommon(String key) {
    this.key = key;
  }

  @Override
  public String get() {
    return key;
  }
}
