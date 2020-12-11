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
@DiscriminatorValue(ClientConstants.COMPANY_PERSON)
public class CompanyPerson extends Client {

  private static final long serialVersionUID = 1L;

  CompanyPerson() {
    super();
  }

  CompanyPerson(ClientData data) {
    super(data);
    setCnpj(data.getDocument());
  }

  public String getCnpj() {
    try {
      return DocumentType.CNPJ.formatted(super.getDocument());
    } catch (ParseException e) {
      throw SelfServiceException.valueOf(I18nCommon.DOCUMENT_CNPJ_INVALID, super.getDocument());
    }
  }

  public void setCnpj(String document) {
    Checker.notBlankTrim(document, I18nClient.CLIENT_DOCUMENT_NOTBLANK);
    Checker.isTrue(DocumentUtils.isCnpj(document), I18nCommon.DOCUMENT_CNPJ_INVALID, document);
    super.setDocument(document);
  }
}
