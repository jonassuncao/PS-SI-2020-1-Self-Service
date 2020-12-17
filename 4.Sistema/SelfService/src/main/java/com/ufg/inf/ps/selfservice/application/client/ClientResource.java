package com.ufg.inf.ps.selfservice.application.client;

import com.ufg.inf.ps.selfservice.application.client.command.CreateClientCommand;
import com.ufg.inf.ps.selfservice.application.client.response.ClientResponse;
import com.ufg.inf.ps.selfservice.domain.client.Client;
import com.ufg.inf.ps.selfservice.domain.client.ClientRepository;
import com.ufg.inf.ps.selfservice.domain.client.SecurityUtils;
import com.ufg.inf.ps.selfservice.infra.response.ResponseFactory;
import com.ufg.inf.ps.selfservice.infra.security.SelfServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;
import java.util.function.Supplier;

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
  @Autowired
  private ClientRepository clientRepository;

  @GetMapping
  public ResponseEntity<ClientResponse> getAccount() {
    return SecurityUtils.identity().flatMap(this::account).orElseGet(forbidden());
  }

  private Optional<ResponseEntity<ClientResponse>> account(SelfServiceDetails details) {
    return clientRepository.findById(details.getUserId()).map(ClientResponse::new).map(ResponseEntity::ok);
  }

  private Supplier<ResponseEntity<ClientResponse>> forbidden() {
    return () -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }


  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody CreateClientCommand command) {
    final Client result = clientApplicationService.when(command);
    return ResponseFactory.created(ENTITY_NAME, result);
  }
}
