package com.ufg.inf.ps.selfservice.infra.exception;

/**
 * @author guilherme.pacheco
 */
public enum ErrorCode {

  INVALID_PARAM("error.invalidParam"),
  ACCESS_DENIED("error.accessDenied"),
  INVALID_OPERATION("error.invalidOperation"),
  INTERNAL_SERVER_ERROR("error.internalServerError");

  private final String code;

  private ErrorCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

}
