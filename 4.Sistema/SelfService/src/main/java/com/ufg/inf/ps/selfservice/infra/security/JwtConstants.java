package com.ufg.inf.ps.selfservice.infra.security;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
public final class JwtConstants {

  public static final String TOKEN_BEARER = "Bearer ";
  public static final String ACCESS_TOKEN = "access_token";
  public static final String AUTHORITIES_KEY = "auth";

  private JwtConstants() {
    super();
  }

}
