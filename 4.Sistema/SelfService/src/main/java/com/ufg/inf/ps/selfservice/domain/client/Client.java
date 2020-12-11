package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.application.client.ClientData;
import com.ufg.inf.ps.selfservice.infra.commons.Checker;
import com.ufg.inf.ps.selfservice.infra.commons.Identification;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nClient;
import com.ufg.inf.ps.selfservice.infra.security.Credential;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@Entity
@DiscriminatorColumn(name = "type")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public abstract class Client extends Identification implements Credential {

  private static final long serialVersionUID = 1L;

  private String username;
  private boolean active;
  private boolean blocked;
  private String password;
  private String urlImage;
  private String nickname;
  private String name;
  private String address;
  @Type(type = "jsonb")
  private HashMap<String, Object> wayPayments = new HashMap<>();
  private String document;

  Client() {
    super();
  }

  Client(ClientData data) {
    super();
    setUsername(data.getUsername());
    setPassword(data.getPassword());
    data.getUrlImage().ifPresent(this::setUrlImage);
    data.getNickname().ifPresent(this::setNickname);
    setName(data.getName());
    data.getAddress().ifPresent(this::setAddress);
    setDocument(data.getDocument());
    initialize();
  }

  void setUsername(String username) {
    this.username = Checker.notBlankTrim(username, I18nClient.CLIENT_USERNAME_NOTBLANK);
  }

  void setActive(boolean active) {
    this.active = active;
  }

  void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  void setPassword(String password) {
    this.password = Checker.notBlankTrim(password, I18nClient.CLIENT_PASSWORD_NOTBLANK);
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
    this.name = Checker.notBlankTrim(name, I18nClient.CLIENT_NAME_NOTBLANK);
  }

  public String getAddress() {
    return address;
  }

  void setAddress(String address) {
    this.address = address;
  }

  public String getDocument() {
    return document;
  }

  void setDocument(String document) {
    this.document = Checker.notBlankTrim(document, I18nClient.CLIENT_DOCUMENT_NOTBLANK);
  }

  public HashMap<String, Object> getWayPayments() {
    return wayPayments;
  }

  void setWayPayments(HashMap<String, Object> wayPayments) {
    this.wayPayments = wayPayments;
  }

  void actived() {
    active = true;
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
    return getId();
  }


}
