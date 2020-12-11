package com.ufg.inf.ps.selfservice.infra.response;

/**
 * @author guilherme.pacheco
 */
public enum AlertMessageType implements AlertMessageFormat {

  DELETED("%s.deleted"),
  UPDATED("%s.updated"),
  CREATED("%s.created");

  private final String template;

  AlertMessageType(String template) {
    this.template = template;
  }

  @Override
  public String format(String value) {
    return String.format(template, value);
  }

}
