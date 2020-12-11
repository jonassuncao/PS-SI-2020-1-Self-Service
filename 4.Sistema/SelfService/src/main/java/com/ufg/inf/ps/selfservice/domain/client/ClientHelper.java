package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.application.client.ClientData;
import com.ufg.inf.ps.selfservice.application.client.command.CreateClientCommand;
import org.springframework.stereotype.Component;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
@Component
public class ClientHelper {

  public ClientData transform(CreateClientCommand command) {
    ClientData data = new ClientData();
    data.setUsername(command.getUsername());
    data.setPassword(command.getPassword());
    command.getNickname().ifPresent(data::setNickname);
    data.setName(command.getName());
    command.getAddress().ifPresent(data::setAddress);
    data.setDocument(command.getDocument());
    command.getBusinessAddress().ifPresent(data::setBusinessAddress);
    command.getMunicipalRegistration().ifPresent(data::setMunicipalRegistration);
    return data;
  }
}
