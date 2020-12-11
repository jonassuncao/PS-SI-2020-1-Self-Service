package com.ufg.inf.ps.selfservice.application.client;

import com.ufg.inf.ps.selfservice.application.client.command.CreateClientCommand;
import com.ufg.inf.ps.selfservice.domain.client.Client;
import com.ufg.inf.ps.selfservice.infra.response.ResponseFactory;
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
@RequestMapping("/api/clients")
public class ClientResource {

  private static final String ENTITY_NAME = "client";

  @Autowired
  private ClientApplicationService clientApplicationService;

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody CreateClientCommand command) {
    final Client result = clientApplicationService.when(command);
    return ResponseFactory.created(ENTITY_NAME, result);
  }
}
