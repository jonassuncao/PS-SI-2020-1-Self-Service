package com.ufg.inf.ps.selfservice.domain.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@Entity
@DiscriminatorValue("COMPANY_PERSON")
public class CompanyPerson extends SelfServiceClient {

  private static final long serialVersionUID = 1L;

  CompanyPerson() {
  }

  public String getCnpj() {
    return super.getDocument();
  }

  public void setCnpj(String document) {
    //Valid is CNPJ
    super.setDocument(document);
  }
}
