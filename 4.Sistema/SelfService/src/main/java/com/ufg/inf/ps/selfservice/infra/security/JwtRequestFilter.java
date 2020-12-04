package com.ufg.inf.ps.selfservice.infra.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtTokenLoader jwtTokenLoader;
  private final ContextLoader contextLoader;
  private final JwtHelper jwtHelper;

  public JwtRequestFilter(JwtTokenLoader jwtTokenLoader, JwtHelper jwtHelper, ContextLoader contextLoader) {
    this.jwtTokenLoader = jwtTokenLoader;
    this.contextLoader = contextLoader;
    this.jwtHelper = jwtHelper;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException {
    try {
      jwtHelper.extractToken(req).map(jwtTokenLoader::authentication).ifPresent(contextLoader::load);
      chain.doFilter(req, res);
    } catch (Exception ex) {
      jwtHelper.loadError(res, ex);
    } finally {
      contextLoader.clean();
    }
  }

}