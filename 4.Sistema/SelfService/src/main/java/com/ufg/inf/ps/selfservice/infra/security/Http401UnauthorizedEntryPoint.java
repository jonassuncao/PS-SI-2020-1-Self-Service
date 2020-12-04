package com.ufg.inf.ps.selfservice.infra.security;

import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private MessageResolver messageResolver;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException {
    log.debug("Pre-authenticated entry point called. Rejecting access");
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message());
  }

  private String message() {
    return messageResolver.resolve(I18nCommon.ERROR_UNAUTHORIZED);
  }
}
