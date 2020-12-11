package com.ufg.inf.ps.selfservice.infra.commons;

import com.ufg.inf.ps.selfservice.infra.exception.SelfServiceException;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Optional;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
public enum DocumentType implements DocumentValidator {

  CPF("###.###.###-##") {
    @Override
    public boolean isValid(String document) {
      String value = sanitize(document);
      return DocumentValidators.isValidCpf(value);
    }
  },
  CNPJ("##.###.###/####-##") {
    @Override
    public boolean isValid(String document) {
      String value = sanitize(document);
      return DocumentValidators.isValidCnpj(value);
    }
  };

  private final MaskFormatter maskFormatter;

  DocumentType(String mask) {
    maskFormatter = FormatUtils.maskFormatter(mask);
  }

  public static DocumentType valueOfByDocumentSize(String document) {
    return ofByDocumentSize(document).orElseThrow(SelfServiceException.supplier(I18nCommon.DOCUMENT_INVALID, document));
  }

  public static Optional<DocumentType> ofByDocumentSize(String value) {
    if (StringUtils.isNotBlank(value)) {
      String sanitizedValue = sanitize(value);
      if (sanitizedValue.length() == DocumentValidators.CPF_SIZE) {
        return Optional.of(DocumentType.CPF);
      }
      if (sanitizedValue.length() == DocumentValidators.CNPJ_SIZE) {
        return Optional.of(DocumentType.CNPJ);
      }
    }
    return Optional.empty();
  }

  public static String sanitize(String value) {
    return RegExUtils.removePattern(String.valueOf(value), "[^a-zA-Z0-9]");
  }

  public String formatted(String value) throws ParseException {
    return maskFormatter.valueToString(value);
  }
}
