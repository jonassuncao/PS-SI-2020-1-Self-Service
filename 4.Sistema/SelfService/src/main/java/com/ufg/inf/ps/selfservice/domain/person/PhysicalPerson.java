package com.ufg.inf.ps.selfservice.domain.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@Entity
@DiscriminatorValue("PHYSICAL_PERSON")
public class PhysicalPerson extends SelfServiceClient {

  private static final long serialVersionUID = 1L;

  PhysicalPerson() {
  }

  public String getCpf() {
    return super.getDocument();
  }

  public void setCpf(String document) {
    //Valid is CPF
    super.setDocument(document);
  }
}
