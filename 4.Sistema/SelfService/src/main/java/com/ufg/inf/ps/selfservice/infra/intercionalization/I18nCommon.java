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
  BAD_CREDENTIALS("bad.credentials"),
  INVALID_AUTHENTICATION("invalid.authentication"),
  LOCKED_USER("locked.user"),
  DISABLED_USER("disabled.user"),
  // Common
  DOCUMENT_INVALID("document.invalid"),
  DOCUMENT_CPF_INVALID("document.cpf.invalid"),
  DOCUMENT_CNPJ_INVALID("document.cnpj.invalid"),
  ;

  private final String key;

  I18nCommon(String key) {
    this.key = key;
  }

  @Override
  public String get() {
    return key;
  }
}
