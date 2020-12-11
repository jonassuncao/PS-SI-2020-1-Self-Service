package com.ufg.inf.ps.selfservice.infra.commons;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
public final class DocumentUtils {

  public static boolean isCpf(String value) {
    return type(value).equals(DocumentType.CPF);
  }

  public static boolean isCnpj(String value) {
    return type(value).equals(DocumentType.CNPJ);
  }

  public static DocumentType type(String value) {
    return DocumentType.valueOfByDocumentSize(value);
  }

}
