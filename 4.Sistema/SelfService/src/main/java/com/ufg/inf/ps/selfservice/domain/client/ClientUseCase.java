package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.application.client.ClientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
@Service
public class ClientUseCase {

  @Autowired
  private ClientFactory clientFactory;
  @Autowired
  private ClientChecker clientChecker;
  @Autowired
  private ClientRepository clientRepository;

  public Client create(ClientData data) {
    Client client = clientFactory.build(data);
    clientChecker.check(client);
    client.actived();
    return clientRepository.save(client);
  }
}
