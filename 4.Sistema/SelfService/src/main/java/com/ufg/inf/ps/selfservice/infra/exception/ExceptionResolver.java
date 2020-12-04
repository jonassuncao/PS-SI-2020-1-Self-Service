package com.ufg.inf.ps.selfservice.infra.exception;

import com.ufg.inf.ps.selfservice.infra.exception.errorbuilder.ErrorFactory;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
@Component
public class ExceptionResolver {

  private final MessageResolver message;
  private final ErrorFactory errorFactory;

  public ExceptionResolver(MessageResolver message, ErrorFactory errorFactory) {
    this.message = message;
    this.errorFactory = errorFactory;
  }

  public ResponseEntity<Error> resolve(Exception ex) {
    return errorFactory.error(ex, message::resolve);
  }


}
