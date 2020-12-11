package com.ufg.inf.ps.selfservice.infra.intercionalization;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
public enum I18nClient implements I18nKey {

  CLIENT_DOCUMENT_NOTBLANK("client.document.notBlank"),
  CLIENT_NAME_NOTBLANK("client.name.notBlank"),
  CLIENT_PASSWORD_NOTBLANK("client.password.notBlank"),
  CLIENT_USERNAME_NOTBLANK("client.username.notBlank"),
  CLIENT_MUNICIPALREGISTRATION_NOTBLANK("client.municipalRegistration.notBlank"),
  CLIENT_BUSINESSADDRESS_NOTBLANK("client.businessAddress.notBlank"),
  CLIENT_MUNICIPALREGISTRATION_REQUIRED("client.municipalRegistration.required"),
  CLIENT_BUSINESSADDRESS_REQUIRED("client.businessAddress.required"),
  CLIENT_INVALID_PASSWORD("client.invalid.password"),
  CLIENT_USERNAME_INUSE("client.username.inUse"),
  CLIENT_USERNAME_INVALID("client.username.invalid"),
  ;

  private final String key;

  I18nClient(String key) {
    this.key = key;
  }

  @Override
  public String get() {
    return key;
  }
}
