package com.ufg.inf.ps.selfservice.infra.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
public class ParamExtractor {
  private final String requestParam;
  private final String headerParam;

  public ParamExtractor(String requestParam, String headerParam) {
    this.requestParam = requestParam;
    this.headerParam = headerParam;
  }

  public Optional<String> get(NativeWebRequest request) {
    return ParamExtractor.param(request, requestParam).or(() -> ParamExtractor.header(request, headerParam));
  }

  public Optional<String> get(HttpServletRequest request) {
    return this.get(new DispatcherServletWebRequest(request));
  }

  public static Optional<String> param(NativeWebRequest request, String requestParam) {
    if (WebUtils.containsParam(request, requestParam)) {
      String value = request.getParameter(requestParam);
      return Optional.ofNullable(value).filter(StringUtils::isNotBlank);
    }
    return Optional.empty();
  }

  public static Optional<String> header(NativeWebRequest request, String headerParam) {
    if (WebUtils.containsHeader(request, headerParam)) {
      String value = request.getHeader(headerParam);
      return Optional.ofNullable(value).filter(StringUtils::isNotBlank);
    }
    return Optional.empty();
  }

  public static Optional<String> header(HttpServletRequest request, String headerParam) {
    return ParamExtractor.header(new DispatcherServletWebRequest(request), headerParam);
  }

  public static Optional<String> param(HttpServletRequest request, String requestParam) {
    return ParamExtractor.param(new DispatcherServletWebRequest(request), requestParam);
  }
}
