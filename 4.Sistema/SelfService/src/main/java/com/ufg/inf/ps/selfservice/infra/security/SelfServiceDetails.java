package com.ufg.inf.ps.selfservice.infra.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
public class SelfServiceDetails extends User implements Identity {

  private static final long serialVersionUID = 1L;

  private final UUID userId;
  private final String username;

  public SelfServiceDetails(UserIdentity user, Collection<? extends GrantedAuthority> authorities) {
    super(String.valueOf(user.getUserId()), user.getPassword(), authorities);
    userId = user.getUserId();
    username = user.getUsername();
  }


  @Override
  public UUID getUserId() {
    return userId;
  }

  @Override
  public String getUsername() {
    return username;
  }
}
