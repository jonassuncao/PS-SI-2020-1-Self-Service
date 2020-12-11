package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.application.client.ClientData;
import com.ufg.inf.ps.selfservice.infra.commons.DocumentUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
@Component
class ClientFactory {

  private final PasswordEncoder passwordEncoder;
  private final ClientChecker clientChecker;

  public ClientFactory(PasswordEncoder passwordEncoder, ClientChecker clientChecker) {
    this.passwordEncoder = passwordEncoder;
    this.clientChecker = clientChecker;
  }

  public Client build(ClientData data) {
    final Client client = factory(data);
    client.setPassword(encodePassword(client));
    return client;
  }

  private String encodePassword(Client client) {
    clientChecker.checkerPassword(client);
    return passwordEncoder.encode(client.getPassword());
  }

  private Client factory(ClientData data) {
    if (isPhysical(data)) {
      return isPerson(data) ? new PhysicalPerson(data) : new PhysicalSupplier(data);
    }
    return isPerson(data) ? new CompanyPerson(data) : new CompanySupplier(data);
  }

  private boolean isPhysical(ClientData data) {
    return DocumentUtils.isCpf(data.getDocument());
  }

  private boolean isPerson(ClientData data) {
    return data.getBusinessAddress().isEmpty() &&
        data.getMunicipalRegistration().isEmpty();
  }
}
