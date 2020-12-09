package com.ufg.inf.ps.selfservice.infra.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.WebRequest;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
public final class WebUtils {

  private WebUtils() {
    super();
  }

  public static boolean containsParam(WebRequest webRequest, String key) {
    return StringUtils.isNotBlank(webRequest.getParameter(key));
  }

  public static boolean containsHeader(WebRequest webRequest, String key) {
    return StringUtils.isNotBlank(webRequest.getHeader(key));
  }
}
