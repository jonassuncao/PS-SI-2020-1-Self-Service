package com.ufg.inf.ps.selfservice.application.authentication.response;

import java.io.Serializable;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
public class JwtResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private final String token;

  public JwtResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }

}