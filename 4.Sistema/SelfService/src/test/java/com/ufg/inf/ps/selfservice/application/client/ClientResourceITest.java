package com.ufg.inf.ps.selfservice.application.client;

import com.ufg.inf.ps.selfservice.application.client.command.CreateClientCommand;
import com.ufg.inf.ps.selfservice.core.IntegrationTest;
import com.ufg.inf.ps.selfservice.core.RestTestUtils;
import com.ufg.inf.ps.selfservice.domain.client.Client;
import com.ufg.inf.ps.selfservice.domain.client.ClientRepository;
import com.ufg.inf.ps.selfservice.domain.client.CompanyPerson;
import com.ufg.inf.ps.selfservice.domain.client.CompanySupplier;
import com.ufg.inf.ps.selfservice.domain.client.PhysicalPerson;
import com.ufg.inf.ps.selfservice.domain.client.PhysicalSupplier;
import com.ufg.inf.ps.selfservice.domain.client.Supplier;
import com.ufg.inf.ps.selfservice.domain.client.SupplierStatus;
import com.ufg.inf.ps.selfservice.infra.commons.DomainUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
class ClientResourceITest extends IntegrationTest {

  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void client_physicalPerson() throws Exception {
    CreateClientCommand command = new CreateClientCommand();
    command.setUsername("pessoa_fisica@email.com");
    command.setPassword("@email.com");
    command.setNickname("apelido");
    command.setName("Pessoa Física");
    command.setAddress("Rua 123, 456");
    command.setDocument("05510375043");

    ResultActions actions = mockMvc().perform(post("/api/clients")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(command)))
        .andExpect(status().isCreated());

    Object alert = RestTestUtils.extractHeaderAlert(actions);
    assertThat(alert).isEqualTo("client.created");

    UUID id = RestTestUtils.extractUuidHeaderParams(actions);
    Client result = clientRepository.getOne(id);

    assertThat(result.getUsername()).isEqualTo("pessoa_fisica@email.com");
    assertThat(passwordEncoder.matches("@email.com", result.getPassword())).isTrue();
    assertThat(result.getNickname()).isEqualTo("apelido");
    assertThat(result.getName()).isEqualTo("Pessoa Física");
    assertThat(result.getAddress()).contains("Rua 123, 456");
    assertThat(result.getDocument()).isEqualTo("05510375043");
    assertThat(result.isActive()).isTrue();
    assertThat(result.isBlocked()).isFalse();
    assertThat(result).isInstanceOf(PhysicalPerson.class);
  }

  @Test
  void client_physicalSupplier() throws Exception {
    CreateClientCommand command = new CreateClientCommand();
    command.setUsername("fornecedor_fisica@email.com");
    command.setPassword("@email.com");
    command.setNickname("apelido");
    command.setName("Fornecedor Físico");
    command.setAddress("Rua 123, 456");
    command.setDocument("05510375043");
    command.setBusinessAddress("Rua empresa 3, 567");
    command.setMunicipalRegistration("123456");

    ResultActions actions = mockMvc().perform(post("/api/clients")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(command)))
        .andExpect(status().isCreated());

    Object alert = RestTestUtils.extractHeaderAlert(actions);
    assertThat(alert).isEqualTo("client.created");

    UUID id = RestTestUtils.extractUuidHeaderParams(actions);
    Client result = clientRepository.getOne(id);

    assertThat(result.getUsername()).isEqualTo("fornecedor_fisica@email.com");
    assertThat(passwordEncoder.matches("@email.com", result.getPassword())).isTrue();
    assertThat(result.getNickname()).isEqualTo("apelido");
    assertThat(result.getName()).isEqualTo("Fornecedor Físico");
    assertThat(result.getAddress()).contains("Rua 123, 456");
    assertThat(result.getDocument()).isEqualTo("05510375043");
    assertThat(result.isActive()).isTrue();
    assertThat(result.isBlocked()).isFalse();
    assertThat(result).isInstanceOf(PhysicalSupplier.class);
    assertThat(DomainUtils.ifCast(result, PhysicalSupplier.class)).map(Supplier::getBusinessAddress).contains("Rua empresa 3, 567");
    assertThat(DomainUtils.ifCast(result, PhysicalSupplier.class)).map(Supplier::getMunicipalRegistration).contains("123456");
    assertThat(DomainUtils.ifCast(result, PhysicalSupplier.class)).map(Supplier::getStatus).contains(SupplierStatus.REGISTERED);
  }

  @Test
  void client_companyPerson() throws Exception {
    CreateClientCommand command = new CreateClientCommand();
    command.setUsername("pessoa_juridica@email.com");
    command.setPassword("@email.com");
    command.setNickname("apelido");
    command.setName("Pessoa Jurídica");
    command.setAddress("Rua 123, 456");
    command.setDocument("29777371000166");

    ResultActions actions = mockMvc().perform(post("/api/clients")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(command)))
        .andExpect(status().isCreated());

    Object alert = RestTestUtils.extractHeaderAlert(actions);
    assertThat(alert).isEqualTo("client.created");

    UUID id = RestTestUtils.extractUuidHeaderParams(actions);
    Client result = clientRepository.getOne(id);

    assertThat(result.getUsername()).isEqualTo("pessoa_juridica@email.com");
    assertThat(passwordEncoder.matches("@email.com", result.getPassword())).isTrue();
    assertThat(result.getNickname()).isEqualTo("apelido");
    assertThat(result.getName()).isEqualTo("Pessoa Jurídica");
    assertThat(result.getAddress()).contains("Rua 123, 456");
    assertThat(result.getDocument()).isEqualTo("29777371000166");
    assertThat(result).isInstanceOf(CompanyPerson.class);
  }

  @Test
  void client_companySupplier() throws Exception {
    CreateClientCommand command = new CreateClientCommand();
    command.setUsername("fornecedor_juridico@email.com");
    command.setPassword("@email.com");
    command.setNickname("apelido");
    command.setName("Fornecedor Jurídico");
    command.setAddress("Rua 123, 456");
    command.setDocument("29777371000166");
    command.setBusinessAddress("Rua empresa 3, 567");
    command.setMunicipalRegistration("123456");

    ResultActions actions = mockMvc().perform(post("/api/clients")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(command)))
        .andExpect(status().isCreated());

    Object alert = RestTestUtils.extractHeaderAlert(actions);
    assertThat(alert).isEqualTo("client.created");

    UUID id = RestTestUtils.extractUuidHeaderParams(actions);
    Client result = clientRepository.getOne(id);

    assertThat(result.getUsername()).isEqualTo("fornecedor_juridico@email.com");
    assertThat(passwordEncoder.matches("@email.com", result.getPassword())).isTrue();
    assertThat(result.getNickname()).isEqualTo("apelido");
    assertThat(result.getName()).isEqualTo("Fornecedor Jurídico");
    assertThat(result.getAddress()).contains("Rua 123, 456");
    assertThat(result.getDocument()).isEqualTo("29777371000166");
    assertThat(result.isActive()).isTrue();
    assertThat(result.isBlocked()).isFalse();
    assertThat(result).isInstanceOf(CompanySupplier.class);
    assertThat(DomainUtils.ifCast(result, CompanySupplier.class)).map(Supplier::getBusinessAddress).contains("Rua empresa 3, 567");
    assertThat(DomainUtils.ifCast(result, CompanySupplier.class)).map(Supplier::getMunicipalRegistration).contains("123456");
    assertThat(DomainUtils.ifCast(result, CompanySupplier.class)).map(Supplier::getStatus).contains(SupplierStatus.REGISTERED);
  }
}