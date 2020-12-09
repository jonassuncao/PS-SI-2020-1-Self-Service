package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;

import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @author jonathas.assuncao on 08/12/2020
 * @project SelfService
 */
public class InternalAuthenticationServiceExceptionBuilder implements ErrorResponseBuilder<InternalAuthenticationServiceException> {

  @Override
  public ResponseEntity<Error> build(InternalAuthenticationServiceException exception, MessageBuilder messageBuilder) {
    String message = messageBuilder.message(I18nCommon.INVALID_AUTHENTICATION);
    Error error = new Error(ErrorCode.ACCESS_DENIED, message);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  public boolean accept(Exception exception) {
    return InternalAuthenticationServiceException.class.isInstance(exception);
  }
}
