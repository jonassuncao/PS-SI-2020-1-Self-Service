package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;

import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

/**
 * @author jonathas.assuncao on 13/11/2020
 * @project pdv
 */
public class BindExceptionErrorBuilder implements ErrorResponseBuilder<BindException> {

  @Override
  public ResponseEntity<Error> build(BindException exception, MessageBuilder messageBuilder) {
    final String field = exception.getFieldError().getField();
    final String message = exception.getFieldError().getDefaultMessage();
    return invalidParam(field, message);
  }

  private ResponseEntity<Error> invalidParam(String field, String message) {
    String text = String.format("%s: %s", field, message);
    Error error = new Error(ErrorCode.INVALID_PARAM, text);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  public boolean accept(Exception exception) {
    return BindException.class.isInstance(exception);
  }
}
