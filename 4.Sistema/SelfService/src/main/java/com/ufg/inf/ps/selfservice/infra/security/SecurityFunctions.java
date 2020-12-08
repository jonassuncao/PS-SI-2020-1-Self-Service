package com.ufg.inf.ps.selfservice.infra.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.function.Supplier;

/**
 * @author jonathas.assuncao on 07/12/2020
 * @project SelfService
 */
public final class SecurityFunctions {

  private SecurityFunctions() {
    super();
  }

  public static Supplier<UsernameNotFoundException> notFound(String username) {
    return () -> new UsernameNotFoundException(String.format("Usuário: %s, não foi encontrado", username));
  }

  public static Supplier<BadCredentialsException> notAutenthicated() {
    return () -> new BadCredentialsException("Usuário não autenticado");
  }

}
