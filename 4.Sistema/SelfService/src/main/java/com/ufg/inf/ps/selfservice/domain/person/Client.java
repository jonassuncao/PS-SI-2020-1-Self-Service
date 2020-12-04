package com.ufg.inf.ps.selfservice.domain.person;

import com.ufg.inf.ps.selfservice.infra.commons.Identification;
import com.ufg.inf.ps.selfservice.infra.security.Credential;
import org.hibernate.annotations.Type;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@MappedSuperclass
@DiscriminatorColumn(name = "type")
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
  private String document;
  @Type(type = "jsonb")
  private HashMap<String, String> wayPayments;

  Client() {
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

  String getDocument(){
    return document;
  }

  void setDocument(String document){
    this.document = document;
  }

  public HashMap<String, String> getWayPayments() {
    return wayPayments;
  }

  void setWayPayments(HashMap<String, String> wayPayments) {
    this.wayPayments = wayPayments;
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
