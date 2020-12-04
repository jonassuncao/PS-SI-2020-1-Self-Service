package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;

import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.exception.SelfServiceException;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author jonathas.assuncao on 13/11/2020
 * @project pdv
 */
public final class SelfServiceErrorBuilder implements ErrorResponseBuilder<SelfServiceException> {

  @Override
  public ResponseEntity<Error> build(SelfServiceException exception, MessageBuilder messageBuilder) {
    String message = messageBuilder.message(exception.getMessageException());
    Error error = new Error(ErrorCode.INVALID_PARAM, message);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  public boolean accept(Exception exception) {
    return SelfServiceException.class.isInstance(exception);
  }

}
