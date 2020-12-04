package com.ufg.inf.ps.selfservice.infra.exception;


import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author jonathas.assuncao on 12/11/2020
 * @project pdv
 */
public class MessageException {

  private final String key;
  private final List<Object> params = new ArrayList<>(1);

  public MessageException(String key) {
    this.key = key;
  }

  public MessageException(String key, Collection<Object> params) {
    this(key);
    this.params.addAll(params);
  }

  public static Supplier<MessageException> supplier(String key, Object... params) {
    return () -> MessageException.valueOf(key, params);
  }

  public static Supplier<MessageException> supplier(I18nKey i18nKey, Object... params) {
    return supplier(i18nKey.get(), params);
  }

  public static MessageException valueOf(String key, Object... params) {
    return new MessageException(key, Arrays.asList(params));
  }

  public String getKey() {
    return key;
  }

  public List<Object> getParams() {
    return params;
  }

  public Object[] getArrayParams() {
    return params.toArray(new Object[params.size()]);
  }

  public String format() {
    return String.format(key, getArrayParams());
  }
}
