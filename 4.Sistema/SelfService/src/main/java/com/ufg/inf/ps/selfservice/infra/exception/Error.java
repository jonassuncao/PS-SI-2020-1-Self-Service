package com.ufg.inf.ps.selfservice.infra.exception;

/**
 * @author guilherme.pacheco
 */
public final class Error {

  private ErrorCode code;
  private String message;
  private Object param;

  Error() {
    super();
  }

  public Error(ErrorCode code, String message) {
    this.message = message;
    this.code = code;
  }

  public Error(ErrorCode code, String message, Object param) {
    this(code, message);
    setParam(param);
  }

  public String getCode() {
    return code.toString();
  }

  public String getMessage() {
    return message;
  }

  public Object getParam() {
    return param;
  }

  private void setParam(Object param) {
    this.param = param;
  }

  @Override
  public String toString() {
    return String.format("Error [code=%s, message=%s, param=%s]", code, message, param);
  }

}
