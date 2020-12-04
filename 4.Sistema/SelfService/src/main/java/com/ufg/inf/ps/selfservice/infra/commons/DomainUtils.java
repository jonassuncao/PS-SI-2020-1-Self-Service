package com.ufg.inf.ps.selfservice.infra.commons;

import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project pdv
 */
public final class DomainUtils {

  private DomainUtils() {
    super();
  }

  public static <T> boolean isUpdated(T oldValue, T newValue) {
    return !Objects.equals(newValue, oldValue);
  }

  public static boolean instanceOf(Object object, Class<?> type) {
    if (object == null) {
      return false;
    }
    Object value = Hibernate.unproxy(object);
    return type.isInstance(value);
  }

  public static <T> T cast(Object object, Class<T> type) {
    Object value = Hibernate.unproxy(object);
    return type.cast(value);
  }

  public static <T> Function<Object, T> cast(Class<T> type) {
    return object -> {
      Object value = Hibernate.unproxy(object);
      return type.cast(value);
    };
  }

  @SuppressWarnings("unchecked")
  public static <T> T unproxy(T value) {
    return (T) Hibernate.unproxy(value);
  }

  public static <T> Optional<T> ifCast(Object object, Class<T> type) {
    if (instanceOf(object, type)) {
      return Optional.of(cast(object, type));
    }
    return Optional.empty();
  }

  public static <T> Function<Object, Optional<T>> ifCast(Class<T> type) {
    return object -> DomainUtils.ifCast(object, type);
  }

}
