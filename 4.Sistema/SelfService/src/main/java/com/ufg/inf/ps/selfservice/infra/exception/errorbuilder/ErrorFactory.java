package com.ufg.inf.ps.selfservice.infra.exception.errorbuilder;

import com.ufg.inf.ps.selfservice.infra.exception.Error;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorCode;
import com.ufg.inf.ps.selfservice.infra.exception.ErrorResponseBuilder;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import com.ufg.inf.ps.selfservice.infra.intercionalization.MessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author jonathas.assuncao on 13/11/2020
 * @project pdv
 */
@Component
public class ErrorFactory {

  private static final String EXCEPTION_NOT_MAPPED = "Exception not mapped: {}";

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final Set<ErrorResponseBuilder<?>> builders = new LinkedHashSet<>();

  ErrorFactory() {
    builders.add(new InvalidDataAccessApiUsageErrorBuilder());
    builders.add(new BadCredentialsExceptionErrorBuilder());
    builders.add(new IllegalArgumentExceptionErrorBuilder());
    builders.add(new BindExceptionErrorBuilder());
    builders.add(new JwtExceptionErrorBuilder());
    builders.add(new SelfServiceErrorBuilder());
  }

  public ResponseEntity<Error> error(Exception ex, MessageBuilder builder) {
    return resolveError(ex, builder).orElse(internalError(ex, builder));
  }

  private Optional<ResponseEntity<Error>> resolveError(Exception ex, MessageBuilder messageBuilder) {
    final Optional<ErrorResponseBuilder<?>> builder = builders.stream().filter(b -> b.accept(ex)).findFirst();
    return builder.map(b -> b.call(ex, messageBuilder));
  }

  private ResponseEntity<Error> internalError(Exception ex, MessageBuilder builder) {
    log.warn(EXCEPTION_NOT_MAPPED, ex.getMessage(), ex);
    final String message = builder.message(I18nCommon.ERROR_INTERNAL);
    final Error error = new Error(ErrorCode.INTERNAL_SERVER_ERROR, message);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
