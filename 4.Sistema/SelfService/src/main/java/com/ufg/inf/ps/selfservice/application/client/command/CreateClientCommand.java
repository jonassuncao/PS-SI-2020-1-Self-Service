package com.ufg.inf.ps.selfservice.application.client.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author jonathas.assuncao on 10/12/2020
 * @project SelfService
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateClientCommand implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @NotBlank
  private String username;
  @NotNull
  @NotBlank
  private String password;
  private String nickname;
  @NotNull
  @NotBlank
  private String name;
  private String address;
  @NotNull
  @NotBlank
  private String document;
  private String businessAddress;
  private String municipalRegistration;


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

  public Optional<String> getNickname() {
    return Optional.ofNullable(nickname);
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Optional<String> getAddress() {
    return Optional.ofNullable(address);
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public Optional<String> getBusinessAddress() {
    return Optional.ofNullable(businessAddress);
  }

  public void setBusinessAddress(String businessAddress) {
    this.businessAddress = businessAddress;
  }

  public Optional<String> getMunicipalRegistration() {
    return Optional.ofNullable(municipalRegistration);
  }

  public void setMunicipalRegistration(String municipalRegistration) {
    this.municipalRegistration = municipalRegistration;
  }
}
