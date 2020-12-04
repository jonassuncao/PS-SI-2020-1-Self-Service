package com.ufg.inf.ps.selfservice.infra.commons;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project pdv
 */
public final class OptionalMapper<T, R> {

  private final Optional<T> value;
  private Optional<R> result = Optional.empty();

  public OptionalMapper(T value) {
    super();
    this.value = Optional.ofNullable(value);
  }

  public <I> OptionalMapper<T, R> instance(Class<I> type, Function<I, ? extends R> mapper) {
    if (!result.isPresent()) {
      result = value.flatMap(DomainUtils.ifCast(type)).map(mapper);
    }
    return this;
  }

  public Optional<R> getResult() {
    return result;
  }
}
