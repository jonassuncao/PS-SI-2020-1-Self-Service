package com.ufg.inf.ps.selfservice.domain.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@Entity
@DiscriminatorValue("COMPANY_SUPPLIER")
public class CompanySupplier extends CompanyPerson implements Supplier {

  private static final long serialVersionUID = 1L;

  private String businessAddress;
  private String municipalRegistration;
  @Enumerated(EnumType.STRING)
  private SupplierStatus status;

  CompanySupplier() {
  }

  @Override
  public String getBusinessName() {
    return getName();
  }

  void setBusinessName(String businessName) {
    setName(businessName);
  }

  @Override
  public String getBusinessAddress() {
    return businessAddress;
  }

  void setBusinessAddress(String businessAddress) {
    this.businessAddress = businessAddress;
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
    this.municipalRegistration = municipalRegistration;
  }

  @Override
  public SupplierStatus getStatus() {
    return status;
  }

  void setStatus(SupplierStatus status) {
    this.status = status;
  }
}
