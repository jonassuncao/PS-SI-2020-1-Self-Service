package com.ufg.inf.ps.selfservice.infra.security;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
public interface Credential extends Identity {

  String getUsername();

  boolean isActive();

  boolean isBlocked();

  String getPassword();


}
