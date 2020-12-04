package com.ufg.inf.ps.selfservice.infra.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author guilherme.pacheco
 */
@Component
class ContextLoader {

  public void load(final Authentication authentication) {
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  public void clean() {
    SecurityContextHolder.clearContext();
  }

}
