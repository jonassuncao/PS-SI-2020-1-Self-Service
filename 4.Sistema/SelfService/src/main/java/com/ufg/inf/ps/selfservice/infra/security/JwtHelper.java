package com.ufg.inf.ps.selfservice.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufg.inf.ps.selfservice.infra.exception.ExceptionResolver;
import com.ufg.inf.ps.selfservice.infra.exception.Error;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
@Component
class JwtHelper {

  private final ObjectMapper objectMapper;
  private final ExceptionResolver exceptionResolver;

  public JwtHelper(ExceptionResolver errorRsolver, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.exceptionResolver = errorRsolver;
  }

  public void loadError(HttpServletResponse res, Exception ex) throws IOException {
    ResponseEntity<Error> response = exceptionResolver.resolve(ex);
    String errorJson = objectMapper.writeValueAsString(response.getBody());
    res.setStatus(response.getStatusCodeValue());
    res.getWriter().print(errorJson);
  }

  public Optional<String> extractToken(HttpServletRequest request) {
    return headerToken(request)
        .or(accessToken(request))
        .filter(StringUtils::isNotBlank);
  }

  private Optional<String> headerToken(HttpServletRequest request) {
    return ParamExtractor.header(request, HttpHeaders.AUTHORIZATION)
        .filter(this::isBearer).map(this::sanitizeBearer)
        .filter(StringUtils::isNotBlank);
  }

  private Supplier<Optional<String>> accessToken(HttpServletRequest request) {
    return () -> ParamExtractor.param(request, JwtConstants.ACCESS_TOKEN);
  }

  private String sanitizeBearer(String value) {
    return StringUtils.removeStart(value, JwtConstants.TOKEN_BEARER);
  }

  private boolean isBearer(String value) {
    return StringUtils.startsWith(value, JwtConstants.TOKEN_BEARER);
  }
}
