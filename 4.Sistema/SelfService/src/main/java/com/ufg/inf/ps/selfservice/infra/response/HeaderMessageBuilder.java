package com.ufg.inf.ps.selfservice.infra.response;

import org.springframework.http.HttpHeaders;

/**
 * @author jonathas.assuncao
 */
public final class HeaderMessageBuilder {

  public static final String X_ALERT = "X-alert";
  public static final String X_PARAMS = "X-params";

  HeaderMessageBuilder() {
    super();
  }

  public HttpHeaders createAlert(String message, String param) {
    HttpHeaders headers = createAlert(message);
    headers.add(X_PARAMS, param);
    return headers;
  }

  public HttpHeaders createAlert(String message) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(X_ALERT, message);
    return headers;
  }

}
