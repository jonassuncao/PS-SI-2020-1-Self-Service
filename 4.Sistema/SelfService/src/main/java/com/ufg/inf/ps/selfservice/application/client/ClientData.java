package com.ufg.inf.ps.selfservice.application.client;

import com.ufg.inf.ps.selfservice.domain.client.SupplierStatus;
import com.ufg.inf.ps.selfservice.infra.commons.Identification;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
public class ClientData extends Identification {

  private String username;
  private boolean active;
  private boolean blocked;
  private String password;
  private String urlImage;
  private String nickname;
  private String name;
  private String address;
  private HashMap<String, Object> wayPayments = new HashMap<>();
  private String document;
  private String businessAddress;
  private String municipalRegistration;
  private String type;
  private SupplierStatus status;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Optional<String> getUrlImage() {
    return Optional.ofNullable(urlImage);
  }

  public void setUrlImage(String urlImage) {
    this.urlImage = urlImage;
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

  public HashMap<String, Object> getWayPayments() {
    return wayPayments;
  }

  public void setWayPayments(HashMap<String, Object> wayPayments) {
    this.wayPayments = wayPayments;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Optional<SupplierStatus> getStatus() {
    return Optional.ofNullable(status);
  }

  public void setStatus(SupplierStatus status) {
    this.status = status;
  }
}
