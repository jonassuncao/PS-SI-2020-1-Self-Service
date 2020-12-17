package com.ufg.inf.ps.selfservice.application.client.response;

import com.ufg.inf.ps.selfservice.domain.client.Client;
import com.ufg.inf.ps.selfservice.domain.client.ClientConstants;
import com.ufg.inf.ps.selfservice.domain.client.CompanyPerson;
import com.ufg.inf.ps.selfservice.domain.client.CompanySupplier;
import com.ufg.inf.ps.selfservice.domain.client.PhysicalPerson;
import com.ufg.inf.ps.selfservice.domain.client.PhysicalSupplier;
import com.ufg.inf.ps.selfservice.domain.client.Supplier;
import com.ufg.inf.ps.selfservice.infra.commons.DomainUtils;

/**
 * @author jonathas.assuncao on 17/12/2020
 * @project SelfService
 */
public class ClientResponse {

  private String nickname;
  private String name;
  private String username;
  private String address;
  private String documentWithMask;
  private String document;
  private String businessAddress;
  private String businessName;
  private String municipalRegistration;
  private String type;

  public ClientResponse(Client data) {
    load(data);
    DomainUtils.ifCast(data, PhysicalPerson.class).ifPresent(this::loadPerson);
    DomainUtils.ifCast(data, CompanyPerson.class).ifPresent(this::loadCompany);
    DomainUtils.ifCast(data, PhysicalSupplier.class).ifPresent(this::load);
    DomainUtils.ifCast(data, CompanySupplier.class).ifPresent(this::load);
  }

  private void load(Client data) {
    username = data.getUsername();
    document = data.getDocument();
    address = data.getAddress();
    name = data.getName();
    nickname = data.getNickname();
  }

  private void loadPerson(PhysicalPerson data) {
    documentWithMask = data.getCpf();
    type = ClientConstants.PHYSICAL_PERSON;
  }

  private void loadCompany(CompanyPerson data) {
    documentWithMask = data.getCnpj();
    type = ClientConstants.COMPANY_PERSON;
  }

  private void load(PhysicalSupplier data) {
    loadPerson(data);
    loadSupplier(data);
    type = ClientConstants.PHYSICAL_SUPPLIER;
  }

  private void load(CompanySupplier data) {
    loadCompany(data);
    loadSupplier(data);
    type = ClientConstants.COMPANY_SUPPLIER;
  }

  private void loadSupplier(Supplier data) {
    businessAddress = data.getBusinessAddress();
    businessName = data.getBusinessName();
    municipalRegistration = data.getMunicipalRegistration();
  }

  public String getNickname() {
    return nickname;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public String getAddress() {
    return address;
  }

  public String getDocument() {
    return document;
  }

  public String getDocumentWithMask() {
    return documentWithMask;
  }

  public String getBusinessAddress() {
    return businessAddress;
  }

  public String getBusinessName() {
    return businessName;
  }

  public String getMunicipalRegistration() {
    return municipalRegistration;
  }

  public String getType() {
    return type;
  }
}
