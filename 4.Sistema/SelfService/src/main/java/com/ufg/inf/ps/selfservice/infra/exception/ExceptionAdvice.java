package com.ufg.inf.ps.selfservice.infra.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionAdvice {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ExceptionResolver exceptionResolver;

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Error> runtimeException(RuntimeException ex) {
    logException(ex);
    return exceptionResolver.resolve(ex);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> exception(Exception ex) {
    logException(ex);
    return exceptionResolver.resolve(ex);
  }

  private void logException(Throwable ex) {
    log.error("Error: {}", ex.getMessage(), ex);
  }
}
