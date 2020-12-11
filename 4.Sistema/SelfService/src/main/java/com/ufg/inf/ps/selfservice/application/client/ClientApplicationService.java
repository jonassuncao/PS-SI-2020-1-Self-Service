package com.ufg.inf.ps.selfservice.application.client;

import com.ufg.inf.ps.selfservice.application.client.command.CreateClientCommand;
import com.ufg.inf.ps.selfservice.domain.client.Client;
import com.ufg.inf.ps.selfservice.domain.client.ClientHelper;
import com.ufg.inf.ps.selfservice.domain.client.ClientUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jonathas.assuncao on 10/12/2020
 * @project SelfService
 */
@Component
@Transactional
public class ClientApplicationService {

  @Autowired
  private ClientHelper clientHelper;
  @Autowired
  private ClientUseCase clientUseCase;

  public Client when(CreateClientCommand command) {
    final ClientData data = clientHelper.transform(command);
    return clientUseCase.create(data);
  }
}
