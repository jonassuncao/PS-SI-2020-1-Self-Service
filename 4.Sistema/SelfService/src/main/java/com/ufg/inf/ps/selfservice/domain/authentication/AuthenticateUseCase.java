package com.ufg.inf.ps.selfservice.domain.authentication;

import com.ufg.inf.ps.selfservice.application.authentication.command.LoginCommand;
import com.ufg.inf.ps.selfservice.infra.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
@Service
public class AuthenticateUseCase {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthenticateUseCase(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  public String authenticate(LoginCommand command) {
    final Authentication authentication = authenticationManager.authenticate(command.authentication());
    return jwtTokenProvider.createToken(authentication);
  }
}
