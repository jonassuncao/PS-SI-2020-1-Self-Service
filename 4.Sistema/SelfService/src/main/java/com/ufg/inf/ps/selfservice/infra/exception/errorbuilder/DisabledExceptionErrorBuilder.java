package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;

import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;

/**
 * @author jonathas.assuncao on 08/12/2020
 * @project SelfService
 */
public class DisabledExceptionErrorBuilder implements ErrorResponseBuilder<DisabledException> {

  @Override
  public ResponseEntity<Error> build(DisabledException exception, MessageBuilder messageBuilder) {
    String message = messageBuilder.message(I18nCommon.DISABLED_USER, exception.getMessage());
    Error error = new Error(ErrorCode.ACCESS_DENIED, message);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  public boolean accept(Exception exception) {
    return DisabledException.class.isInstance(exception);
  }
}
