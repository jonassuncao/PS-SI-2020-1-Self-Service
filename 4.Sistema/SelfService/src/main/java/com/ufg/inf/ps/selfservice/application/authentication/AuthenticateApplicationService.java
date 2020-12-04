package com.ufg.inf.ps.selfservice.application.authentication;

import com.ufg.inf.ps.selfservice.application.authentication.command.LoginCommand;
import com.ufg.inf.ps.selfservice.domain.authentication.AuthenticateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
@Component
@Transactional
public class AuthenticateApplicationService {

  @Autowired
  private AuthenticateUseCase authenticateUseCase;

  public String when(LoginCommand command) {
    return authenticateUseCase.authenticate(command);
  }
}
