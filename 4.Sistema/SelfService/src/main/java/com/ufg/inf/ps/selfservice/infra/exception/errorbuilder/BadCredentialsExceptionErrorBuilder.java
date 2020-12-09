package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;

import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * @author jonathas.assuncao on 08/12/2020
 * @project SelfService
 */
public class BadCredentialsExceptionErrorBuilder implements ErrorResponseBuilder<BadCredentialsException> {

  @Override
  public ResponseEntity<Error> build(BadCredentialsException exception, MessageBuilder messageBuilder) {
    String text = messageBuilder.message(I18nCommon.BAD_CREDENTIALS);
    Error error = new Error(ErrorCode.INVALID_PARAM, text);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  public boolean accept(Exception exception) {
    return BadCredentialsException.class.isInstance(exception);
  }
}
