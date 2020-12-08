package com.ufg.inf.ps.selfservice.domain.person;

import com.ufg.inf.ps.selfservice.infra.security.Credential;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@Entity
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("OCCURRENCE")
public class SelfServiceClient implements Credential {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", updatable = false, nullable = false, unique = true)
  private UUID id;

  private String username;
  private boolean active;
  private boolean blocked;
  private String password;
  private String urlImage;
  private String nickname;
  private String name;
  private String address;
  private String document;

  SelfServiceClient() {
    super();
  }

  void setUsername(String username) {
    this.username = username;
  }

  void setActive(boolean active) {
    this.active = active;
  }

  void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  void setPassword(String password) {
    this.password = password;
  }

  public String getUrlImage() {
    return urlImage;
  }

  void setUrlImage(String urlImage) {
    this.urlImage = urlImage;
  }

  public String getNickname() {
    return nickname;
  }

  void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  void setAddress(String address) {
    this.address = address;
  }

  String getDocument() {
    return document;
  }

  void setDocument(String document) {
    this.document = document;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public boolean isBlocked() {
    return blocked;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public UUID getUserId() {
    return id;
  }
}
