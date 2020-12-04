package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;

import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author guilherme.pacheco
 */
final class JwtExceptionErrorBuilder implements ErrorResponseBuilder<JwtException> {

  @Override
  public ResponseEntity<Error> build(JwtException exception, MessageBuilder messageBuilder) {
    Error error = createError(exception, messageBuilder);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
  }

  private Error createError(JwtException exception, MessageBuilder messageBuilder) {
    if (exception instanceof ExpiredJwtException) {
      String message = messageBuilder.message(I18nCommon.TOKEN_EXPIRED);
      return new Error(ErrorCode.ACCESS_DENIED, message);
    }
    String message = messageBuilder.message(I18nCommon.TOKEN_INVALID);
    return new Error(ErrorCode.ACCESS_DENIED, message);
  }

  @Override
  public boolean accept(Exception exception) {
    return JwtException.class.isInstance(exception);
  }

}
