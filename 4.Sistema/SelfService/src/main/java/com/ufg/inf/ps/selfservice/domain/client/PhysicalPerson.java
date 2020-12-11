package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.application.client.ClientData;
import com.ufg.inf.ps.selfservice.infra.commons.Checker;
import com.ufg.inf.ps.selfservice.infra.commons.DocumentType;
import com.ufg.inf.ps.selfservice.infra.commons.DocumentUtils;
import com.ufg.inf.ps.selfservice.infra.exception.SelfServiceException;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nClient;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.text.ParseException;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@Entity
@DiscriminatorValue(ClientConstants.PHYSICAL_PERSON)
public class PhysicalPerson extends Client {

  private static final long serialVersionUID = 1L;

  PhysicalPerson() {
    super();
  }

  PhysicalPerson(ClientData data) {
    super(data);
    setCpf(data.getDocument());
  }

  public String getCpf() {
    try {
      return DocumentType.CPF.formatted(super.getDocument());
    } catch (ParseException e) {
      throw SelfServiceException.valueOf(I18nCommon.DOCUMENT_CPF_INVALID, super.getDocument());
    }
  }

  public void setCpf(String document) {
    Checker.notBlankTrim(document, I18nClient.CLIENT_DOCUMENT_NOTBLANK);
    Checker.isTrue(DocumentUtils.isCpf(document), I18nCommon.DOCUMENT_CPF_INVALID, document);
    super.setDocument(document);
  }
}
