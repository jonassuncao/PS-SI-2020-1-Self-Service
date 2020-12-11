package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.application.client.ClientData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientFactoryTest {

  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private ClientChecker clientChecker;
  @Mock
  private ClientData clientData;
  @Mock
  private ClientFactory clientFactory;

  @BeforeEach
  void setup() throws Exception {
    clientFactory = new ClientFactory(passwordEncoder, clientChecker);

    when(clientData.getUsername()).thenReturn("email@email.com");
    when(clientData.getPassword()).thenReturn("@email.com");
    when(clientData.getName()).thenReturn("Test");
    when(passwordEncoder.encode("@email.com")).thenReturn("encoded");
  }

  @Test
  void build_physicalPerson() throws Exception {
    when(clientData.getDocument()).thenReturn("13313433057");
    when(clientData.getBusinessAddress()).thenReturn(Optional.empty());
    when(clientData.getMunicipalRegistration()).thenReturn(Optional.empty());


    final Client result = clientFactory.build(clientData);

    assertThat(result).isInstanceOf(PhysicalPerson.class);
    verify(passwordEncoder, times(1)).encode(eq("@email.com"));
    verify(clientChecker, times(1)).checkerPassword(any(Client.class));
  }

  @Test
  void build_physicalSupplier() throws Exception {
    when(clientData.getDocument()).thenReturn("13313433057");
    when(clientData.getBusinessAddress()).thenReturn(Optional.of("Rua 123"));
    when(clientData.getMunicipalRegistration()).thenReturn(Optional.of("123456"));


    final Client result = clientFactory.build(clientData);

    assertThat(result).isInstanceOf(PhysicalSupplier.class);
    verify(passwordEncoder, times(1)).encode(eq("@email.com"));
    verify(clientChecker, times(1)).checkerPassword(any(Client.class));
  }

  @Test
  void build_companyPerson() throws Exception {
    when(clientData.getDocument()).thenReturn("66711635000105");
    when(clientData.getBusinessAddress()).thenReturn(Optional.empty());
    when(clientData.getMunicipalRegistration()).thenReturn(Optional.empty());


    final Client result = clientFactory.build(clientData);

    assertThat(result).isInstanceOf(CompanyPerson.class);
    verify(passwordEncoder, times(1)).encode(eq("@email.com"));
    verify(clientChecker, times(1)).checkerPassword(any(Client.class));
  }

  @Test
  void build_companySupplier() throws Exception {
    when(clientData.getDocument()).thenReturn("66711635000105");
    when(clientData.getBusinessAddress()).thenReturn(Optional.of("Rua 123"));
    when(clientData.getMunicipalRegistration()).thenReturn(Optional.of("123456"));


    final Client result = clientFactory.build(clientData);

    assertThat(result).isInstanceOf(CompanySupplier.class);
    verify(passwordEncoder, times(1)).encode(eq("@email.com"));
    verify(clientChecker, times(1)).checkerPassword(any(Client.class));
  }
}