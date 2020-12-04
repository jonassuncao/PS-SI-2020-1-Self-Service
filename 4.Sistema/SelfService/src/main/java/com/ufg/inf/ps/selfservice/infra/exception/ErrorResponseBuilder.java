package com.ufg.inf.ps.selfservice.infra.exception;


import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.springframework.http.ResponseEntity;

/**
 * @author jonathas.assuncao on 13/11/2020
 * @project pdv
 */
public interface ErrorResponseBuilder<T extends Exception> {

  ResponseEntity<Error> build(T exception, MessageBuilder messageBuilder);

  boolean accept(Exception exception);

  default ResponseEntity<Error> call(Exception exception, MessageBuilder messageBuilder) {
    T value = resolve(exception);
    return build(value, messageBuilder);
  }

  @SuppressWarnings("unchecked")
  default T resolve(Exception exception) {
    return (T) exception;
  }
}
