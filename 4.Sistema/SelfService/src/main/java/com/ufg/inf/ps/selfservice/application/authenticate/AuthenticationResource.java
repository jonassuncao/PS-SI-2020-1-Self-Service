package com.ufg.inf.ps.selfservice.application.authenticate;

import com.ufg.inf.ps.selfservice.application.authenticate.command.LoginCommand;
import com.ufg.inf.ps.selfservice.application.authenticate.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationResource {


  @Autowired
  private AuthenticateApplicationService authenticationApplicationSerice;

  @PostMapping
  public ResponseEntity<?> authenticate(@Valid @RequestBody LoginCommand command) {
    final String result = authenticationApplicationSerice.when(command);
    return ResponseEntity.ok(new JwtResponse(result));
  }

}
