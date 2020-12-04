package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;


import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author jonathas.assuncao on 13/11/2020
 * @project pdv
 */
public final class IllegalArgumentExceptionErrorBuilder implements ErrorResponseBuilder<IllegalArgumentException> {

  @Override
  public ResponseEntity<Error> build(IllegalArgumentException exception, MessageBuilder messageBuilder) {
    String message = messageBuilder.message(exception.getMessage());
    Error error = new Error(ErrorCode.INVALID_OPERATION, message);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  public boolean accept(Exception exception) {
    return IllegalArgumentException.class.isInstance(exception);
  }
}
