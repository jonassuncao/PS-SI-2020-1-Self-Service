package com.ufg.inf.ps.selfservice.infra.commons;

import com.ufg.inf.ps.selfservice.infra.exception.MessageException;
import com.ufg.inf.ps.selfservice.infra.exception.NotExistException;
import com.ufg.inf.ps.selfservice.infra.exception.SelfServiceException;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nKey;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project pdv
 */
public class Checker {

  private Checker() {
    super();
  }

  /**
   * Valida se o parâmetro <b>obj</b> não é nulo
   *
   * @param <T>  Tipo do obj
   * @param obj  Objeto a ser validado
   * @param key  mensagem de erro
   * @param args Parâmetros da mensagem
   * @return Objeto validado
   * @throws SelfServiceException se obj for nulo
   */
  public static <T> T notNull(final T obj, I18nKey key, Object... args) {
    return notNull(obj, MessageException.supplier(key, args));
  }

  public static <T> T notNull(final T obj, @NonNull final Supplier<MessageException> params) {
    if (obj == null) {
      throw new SelfServiceException(params.get());
    }
    return obj;
  }

  /**
   * Valite se o parâmetro <b>obj</b> está nulo
   *
   * @param obj  Objeto a ser validado
   * @param key  mensagem de erro
   * @param args Parâmetros da mensagem
   * @throws SelfServiceException se obj não for nulo
   */
  public static void isNull(final Object obj, I18nKey key, Object... args) {
    if (obj != null) {
      throw SelfServiceException.valueOf(key, args);
    }
  }

  /**
   * Valida a existência da entidade <b>obj</b>
   *
   * @param <T>  Tipo do obj
   * @param obj  Objeto a ser validado
   * @param key  mensagem de erro
   * @param args Parâmetros da mensagem
   * @return Objeto validado
   * @throws NotExistException se obj for nulo
   */
  public static <T> T exist(final T obj, I18nKey key, Object... args) {
    return Optional.ofNullable(obj).orElseThrow(() -> new NotExistException(key, args));
  }

  /**
   * Valida se o parâmetro <b>obj</b> não está em vazio
   *
   * @param <T>   the character sequence type
   * @param chars Objeto a ser validado
   * @param key   mensagem de erro
   * @param args  Parâmetros da mensagem
   * @return Objeto validado
   * @throws SelfServiceException se obj for nulo ou vazio
   */
  public static <T extends CharSequence> T notEmpty(final T chars, I18nKey key, Object... args) {
    if (StringUtils.isEmpty(chars)) {
      throw SelfServiceException.valueOf(key, args);
    }
    return chars;
  }

  public static <T extends Collection<?>> T notEmpty(final T collection, I18nKey key, Object... args) {
    if (collection == null || collection.isEmpty()) {
      throw SelfServiceException.valueOf(key, args);
    }
    return collection;
  }

  public static <T extends Map<?, ?>> T notEmpty(final T collection, I18nKey key, Object... args) {
    if (collection == null || collection.isEmpty()) {
      throw SelfServiceException.valueOf(key, args);
    }
    return collection;
  }

  /**
   * Valida se o parâmetro <b>obj</b> não está em branco
   *
   * @param <T>   Tipo do charsequence
   * @param chars Objeto a ser validado
   * @param key   mensagem de erro
   * @param args  Parâmetros da mensagem
   * @return Objeto validado
   * @throws SelfServiceException se obj for nulo ou em branco
   */
  public static <T extends CharSequence> T notBlank(final T chars, I18nKey key, Object... args) {
    if (StringUtils.isBlank(chars)) {
      throw SelfServiceException.valueOf(key, args);
    }
    return chars;
  }

  public static String notBlankTrim(final String chars, I18nKey key, Object... args) {
    return notBlank(chars, key, args).trim();
  }

  /**
   * Valida se o parâmetro <b>value</b> é verdadeiro.
   *
   * @param value Valor a ser validado
   * @param key   Mensagem de erro
   * @param args  Parâmetros da mensagem
   * @throws SelfServiceException caso <b>value</b> seja <code>false</code>
   */
  public static void isTrue(final boolean value, I18nKey key, Object... args) {
    isTrue(value, MessageException.supplier(key, args));
  }

  /**
   * Valida se o parâmetro <b>value</b> é verdadeiro.
   *
   * @param value    Valor a ser validado
   * @param supplier Supplier que montará a mensagem de erro caso falhe a validação.
   * @throws SelfServiceException caso <b>value</b> seja <code>false</code>
   */
  public static void isTrue(final boolean value, final Supplier<MessageException> supplier) {
    if (!value) {
      throw new SelfServiceException(supplier.get());
    }
  }

  /**
   * Valida se o parâmetro <b>value</b> é igual a <b>expected</b>.
   *
   * @param value    Valor a ser validado
   * @param expected Valor esperado
   * @param key      Mensagem de erro
   * @param args     Parâmetros da mensagem
   * @throws SelfServiceException caso os valores não sejam iguais.
   */
  public static <T> T equals(final T value, T expected, I18nKey key, Object... args) {
    isTrue(Objects.equals(value, expected), MessageException.supplier(key, args));
    return value;
  }

  /**
   * Valida se o parâmetro <b>value</b> é falso.
   *
   * @param value Valor a ser validado
   * @param key   Mensagem de erro
   * @param args  Parâmetros da mensagem
   * @throws SelfServiceException caso <b>value</b> seja <code>true</code>
   */
  public static void isFalse(final boolean value, I18nKey key, Object... args) {
    isFalse(value, MessageException.supplier(key, args));
  }

  /**
   * Valida se o parâmetro <b>value</b> é false.
   *
   * @param value    Valor a ser validado
   * @param supplier Supplier que montará a mensagem de erro caso falhe a validação.
   * @throws SelfServiceException caso <b>value</b> seja <code>true</code>
   */
  public static void isFalse(final boolean value, final Supplier<MessageException> supplier) {
    if (value) {
      throw new SelfServiceException(supplier.get());
    }
  }

  /**
   * Valida se o parâmetro <b>value</b> é maior que ZERO.
   *
   * @param value Valor a ser validado
   * @param key   Mnesagem de erro
   * @param args  Parâmetros da mensagem
   * @throws SelfServiceException caso <b>value</b> não seja positivo
   */
  public static BigDecimal positive(final BigDecimal value, I18nKey key, Object... args) {
    notNull(value, key, args);
    isTrue(value.compareTo(BigDecimal.ZERO) > 0, key, args);
    return value;
  }


  /**
   * Valida se o parâmetro <b>value</b> é maior ou igual a ZERO.
   *
   * @param value Valor a ser validado
   * @param key   Mnesagem de erro
   * @param args  Parâmetros da mensagem
   * @throws SelfServiceException caso <b>value</b> seja negativo
   */
  public static BigDecimal zeroOrPositive(final BigDecimal value, I18nKey key, Object... args) {
    notNull(value, key, args);
    isTrue(value.compareTo(BigDecimal.ZERO) >= 0, key, args);
    return value;
  }

  /**
   * Valida se o parâmetro <b>value</b> é interior a data atual.
   *
   * @param value Valor a ser validado
   * @param key   Mnesagem de erro
   * @param args  Parâmetros da mensagem
   * @throws SelfServiceException caso <b>value</b> seja superior a agora
   */
  public static Instant past(Instant value, I18nKey key, Object... args) {
    Checker.notNull(value, key, args);
    if (Instant.now().isBefore(value)) {
      throw SelfServiceException.valueOf(key, args);
    }
    return value;
  }

  /**
   * Valida se o parâmetro <b>value</b> é superior a data atual.
   *
   * @param value Valor a ser validado
   * @param key   Mnesagem de erro
   * @param args  Parâmetros da mensagem
   * @throws SelfServiceException caso <b>value</b> seja inferior a agora
   */
  public static Instant future(Instant value, I18nKey key, Object... args) {
    Checker.notNull(value, key, args);
    if (Instant.now().isAfter(value)) {
      throw SelfServiceException.valueOf(key, args);
    }
    return value;
  }

  /**
   * Valida se o parâmetro <b>value</b> é superior a data atual.
   *
   * @param value Valor a ser validado
   * @param key   Mnesagem de erro
   * @param args  Parâmetros da mensagem
   * @throws SelfServiceException caso <b>value</b> seja inferior a agora
   */
  public static LocalDate future(LocalDate value, I18nKey key, Object... args) {
    Checker.notNull(value, key, args);
    if (LocalDate.now().isAfter(value)) {
      throw SelfServiceException.valueOf(key, args);
    }
    return value;
  }


  /**
   * Valida se o parâmetro não é nulo e se o predicado informado é verdadeiro.
   *
   * @param <T>       Tipo do obj
   * @param obj       Objeto a ser validado
   * @param predicate Condição de validação do objeto
   * @param key       Mensagem de erro
   * @param args      Parâmetros da mensagem
   * @return Objeto validado
   * @throws SelfServiceException se obj for nulo
   */
  public static <T> T valid(final T obj, @NonNull Predicate<T> predicate, I18nKey key, Object... args) {
    notNull(obj, key, args);
    Validate.notNull(predicate, "Invalid predicate");
    isTrue(predicate.test(obj), key, args);
    return obj;
  }

  /**
   * Valida se o parâmetro não é nulo e se o predicado informado é verdadeiro.
   *
   * @param <T>       Tipo do obj
   * @param obj       Objeto a ser validado
   * @param predicate Condição de validação do objeto
   * @param supplier  Supplier que montará a mensagem de erro caso falhe a validação.
   * @throws SelfServiceException se obj for nulo
   */
  public static <T> void optionalTrue(final Optional<T> obj, @NonNull Predicate<T> predicate,
                                      final Supplier<MessageException> supplier) {
    notNull(obj, supplier);
    Validate.notNull(predicate, "Invalid predicate");
    obj.ifPresent(value -> isTrue(predicate.test(value), supplier));
  }

  /**
   * Valida se o parâmetro não é nulo e se o predicado informado é verdadeiro.
   *
   * @param <T>       Tipo do obj
   * @param obj       Objeto a ser validado
   * @param predicate Condição de validação do objeto
   * @param key       Mensagem de erro
   * @param args      Parâmetros da mensagem
   * @throws SelfServiceException se obj for nulo
   */
  public static <T> void optionalTrue(final Optional<T> obj, @NonNull Predicate<T> predicate, I18nKey key,
                                      Object... args) {
    optionalTrue(obj, predicate, MessageException.supplier(key, args));
  }

  /**
   * Valida o tamanho da string está entre mínimo e máxima informado
   *
   * @param chars String a ser validada
   * @param min   Tamanho mínimo
   * @param max   Tamanho máximo
   * @param key   Mensagem de erro
   * @param args  Parâmetros da mensagem
   * @return Valor informado.
   */
  public static <T extends CharSequence> T size(final T chars, int min, int max, I18nKey key, Object... args) {
    min(chars, min, key, args);
    max(chars, max, key, args);
    return chars;
  }

  /**
   * Valida o tamanho da string exatamente
   *
   * @param chars  String a ser validada
   * @param length Tamanho para a validação
   * @param key    Mensagem de erro
   * @param args   Parâmetros da mensagem
   * @return Valor informado.
   * @throws SelfServiceException caso <b>value</b> não tenha o tamanho informado
   */
  public static <T extends CharSequence> T length(final T chars, int length, I18nKey key, Object... args) {
    if (StringUtils.length(chars) != length) {
      throw SelfServiceException.valueOf(key, args);
    }
    return chars;
  }

  /**
   * Valida o tamanho da string exatamente
   *
   * @param chars String a ser validada
   * @param min   Tamanho para a validação
   * @param key   Mensagem de erro
   * @param args  Parâmetros da mensagem
   * @return Valor informado.
   * @throws SelfServiceException caso <b>value</b> seja menor o mínimo informado
   */
  public static <T extends CharSequence> T min(final T chars, int min, I18nKey key, Object... args) {
    if (StringUtils.isBlank(chars)) {
      return chars;
    } else if (StringUtils.length(chars) < min) {
      throw SelfServiceException.valueOf(key, args);
    }
    return chars;
  }

  /**
   * Valida o tamanho da string exatamente
   *
   * @param chars String a ser validada
   * @param max   Tamanho para a validação
   * @param key   Mensagem de erro
   * @param args  Parâmetros da mensagem
   * @return Valor informado.
   * @throws SelfServiceException caso <b>value</b> seja menor o máximo informado
   */
  public static <T extends CharSequence> T max(final T chars, int max, I18nKey key, Object... args) {
    if (StringUtils.isBlank(chars)) {
      return chars;
    } else if (StringUtils.length(chars) > max) {
      throw SelfServiceException.valueOf(key, args);
    }
    return chars;
  }

  /**
   * Valida se <b>value</b> possui um valor.
   *
   * @param value Valor a ser validado
   * @param key   Mensagem de erro
   * @param args  Parâmetro da mensagem
   * @return valor do {@link Optional} validado.
   * @throws SelfServiceException caso <b>value</b> não tenha valor presente.
   */
  public static <T> T present(final Optional<T> value, I18nKey key, Object... args) {
    notNull(value, key, args);
    if (!value.isPresent()) {
      throw SelfServiceException.valueOf(key, args);
    }
    return value.get();
  }

  /**
   * Valida se <b>value</b> não possui um valor.
   *
   * @param value Valor a ser validado
   * @param key   Mensagem de erro
   * @param args  Parâmetro da mensagem
   * @throws SelfServiceException caso <b>value</b> tenha valor presente.
   */
  public static void notPresent(final Optional<?> value, I18nKey key, Object... args) {
    notNull(value, key, args);
    if (value.isPresent()) {
      throw SelfServiceException.valueOf(key, args);
    }
  }

  public static void regex(String regex, String value, I18nKey key, Object... args) {
    if (!Pattern.matches(regex, value)) {
      throw SelfServiceException.valueOf(key, args);
    }
  }

  public static <T> T isInstanceOf(Object object, Class<T> type, I18nKey key, Object... args) {
    if (!DomainUtils.instanceOf(object, type)) {
      throw SelfServiceException.valueOf(key, args);
    }
    return DomainUtils.cast(object, type);
  }

  public static void notInstanceOf(Object object, Class<?> type, I18nKey key, Object... args) {
    if (DomainUtils.instanceOf(object, type)) {
      throw SelfServiceException.valueOf(key, args);
    }
  }

}
