package com.ufg.inf.ps.selfservice.domain.person;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
public interface Supplier {

  String getBusinessName();

  String getBusinessAddress();

  String getBusinessDocument();

  String getMunicipalRegistration();

  SupplierStatus getStatus();

}
