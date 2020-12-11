package com.ufg.inf.ps.selfservice.domain.authenticate;

import com.ufg.inf.ps.selfservice.domain.client.Client;
import com.ufg.inf.ps.selfservice.domain.client.ClientRepository;
import com.ufg.inf.ps.selfservice.infra.security.SecurityFunctions;
import com.ufg.inf.ps.selfservice.infra.security.SelfServiceDetails;
import com.ufg.inf.ps.selfservice.infra.security.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author jonathas.assuncao on 07/12/2020
 * @project SelfService
 */
@Component("userDetailsService")
public class SelfServiceDetailService implements UserDetailsService {

  private final ClientRepository selfServiceClientStore;

  public SelfServiceDetailService(ClientRepository clientRepository) {
    this.selfServiceClientStore = clientRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) {
    Client user = getUser(username);
    UserDetailsChecker.check(user);
    return new SelfServiceDetails(user, Collections.emptyList());
  }

  private Client getUser(String username) {
    return selfServiceClientStore.findByUsername(username).orElseThrow(SecurityFunctions.notFound(username));
  }

}
