package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author jonathas.assuncao on 13/11/2020
 * @project pdv
 */
public final class InvalidDataAccessApiUsageErrorBuilder implements ErrorResponseBuilder<InvalidDataAccessApiUsageException> {

  @Override
  public ResponseEntity<Error> build(InvalidDataAccessApiUsageException exception, MessageBuilder messageBuilder) {
    if (exception.getCause() instanceof IllegalArgumentException) {
      IllegalArgumentException illegalArgumentException = (IllegalArgumentException) exception.getCause();
      if (illegalArgumentException.getCause() instanceof MismatchedInputException) {
        String message = messageBuilder.message(I18nCommon.ERROR_INTERNAL);
        Error error = new Error(ErrorCode.INVALID_OPERATION, message);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return invalidOperation(messageBuilder);
  }

  private ResponseEntity<Error> invalidOperation(MessageBuilder messageBuilder) {
    String message = messageBuilder.message(I18nCommon.INVALID_OPERATION);
    Error error = new Error(ErrorCode.INVALID_OPERATION, message);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  public boolean accept(Exception exception) {
    return InvalidDataAccessApiUsageException.class.isInstance(exception);
  }

}
