package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.application.client.ClientData;
import com.ufg.inf.ps.selfservice.infra.commons.Checker;
import com.ufg.inf.ps.selfservice.infra.exception.SelfServiceException;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nClient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Optional;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@Entity
@DiscriminatorValue(ClientConstants.COMPANY_SUPPLIER)
public class CompanySupplier extends CompanyPerson implements Supplier {

  private static final long serialVersionUID = 1L;

  private String businessAddress;
  private String municipalRegistration;
  @Enumerated(EnumType.STRING)
  private SupplierStatus status;

  CompanySupplier() {
    super();
  }

  CompanySupplier(ClientData data) {
    super(data);
    businessAddress(data.getBusinessAddress());
    municipalRegistration(data.getMunicipalRegistration());
    setBusinessDocument(data.getDocument());
    setStatus(SupplierStatus.REGISTERED);
  }

  private void municipalRegistration(Optional<String> value) {
    final String municipalRegistration = value.orElseThrow(SelfServiceException.supplier(I18nClient.CLIENT_MUNICIPALREGISTRATION_REQUIRED));
    setMunicipalRegistration(municipalRegistration);
  }

  private void businessAddress(Optional<String> value) {
    final String address = value.orElseThrow(SelfServiceException.supplier(I18nClient.CLIENT_BUSINESSADDRESS_REQUIRED));
    setBusinessAddress(address);
  }

  @Override
  public String getBusinessName() {
    return getName();
  }

  @Override
  public String getBusinessAddress() {
    return businessAddress;
  }

  void setBusinessAddress(String businessAddress) {
    this.businessAddress = Checker.notBlankTrim(businessAddress, I18nClient.CLIENT_BUSINESSADDRESS_NOTBLANK);
  }

  @Override
  public String getBusinessDocument() {
    return getCnpj();
  }

  void setBusinessDocument(String businessDocument) {
    setCnpj(businessDocument);
  }

  @Override
  public String getMunicipalRegistration() {
    return municipalRegistration;
  }

  void setMunicipalRegistration(String municipalRegistration) {
    this.municipalRegistration = Checker.notBlankTrim(municipalRegistration, I18nClient.CLIENT_MUNICIPALREGISTRATION_NOTBLANK);
  }

  @Override
  public SupplierStatus getStatus() {
    return status;
  }

  void setStatus(SupplierStatus status) {
    this.status = status;
  }
}
