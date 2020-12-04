package com.ufg.inf.ps.selfservice.application.authentication.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginCommand implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;

  LoginCommand() {
    super();
  }

  public LoginCommand(String username, String password) {
    setUsername(username);
    setPassword(password);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Authentication authentication() {
    return new UsernamePasswordAuthenticationToken(getUsername(), getPassword());
  }
}
