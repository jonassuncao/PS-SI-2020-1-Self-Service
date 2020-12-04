package com.ufg.inf.ps.selfservice.infra.exception;


import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nKey;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
public class SelfServiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final transient List<Object> params;

  public SelfServiceException(String key, List<Object> params) {
    super(key);
    this.params = Objects.nonNull(params) ? params : Collections.emptyList();
  }

  public SelfServiceException(I18nKey key, List<Object> params, Throwable throwable) {
    super(key.get(), throwable);
    this.params = Objects.nonNull(params) ? params : Collections.emptyList();
  }

  public SelfServiceException(I18nKey key, List<Object> params) {
    this(key.get(), params);
  }

  public SelfServiceException(I18nKey key) {
    this(key, Collections.emptyList());
  }

  public SelfServiceException(MessageException messageParams) {
    this(messageParams.getKey(), messageParams.getParams());
  }

  public MessageException getMessageException() {
    return new MessageException(getMessage(), params);
  }

  public List<Object> getParams() {
    return Collections.unmodifiableList(params);
  }

  public static Supplier<SelfServiceException> supplier(I18nKey key, Object... args) {
    return () -> SelfServiceException.valueOf(key, args);
  }

  public static SelfServiceException valueOf(I18nKey key, Object... args) {
    return new SelfServiceException(key, params(args));
  }

  public static SelfServiceException valueOf(Throwable throwable, I18nKey key, Object... args) {
    return new SelfServiceException(key, params(args), throwable);
  }

  public static SelfServiceException valueOf(I18nKey key) {
    return new SelfServiceException(key);
  }

  protected static List<Object> params(Object... args) {
    return Arrays.stream(args).filter(Objects::nonNull).collect(Collectors.toList());
  }
}
