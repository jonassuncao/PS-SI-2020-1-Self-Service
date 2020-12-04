package com.ufg.inf.ps.selfservice.infra.exception;


import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nKey;

import java.util.List;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
public class NotExistException extends SelfServiceException {

  private static final long serialVersionUID = 1L;

  public NotExistException(I18nKey key, List<Object> params) {
    super(key, params);
  }

  public NotExistException(I18nKey key, Object... args) {
    super(key, params(args));
  }

  public NotExistException(I18nKey key) {
    super(key);
  }

}