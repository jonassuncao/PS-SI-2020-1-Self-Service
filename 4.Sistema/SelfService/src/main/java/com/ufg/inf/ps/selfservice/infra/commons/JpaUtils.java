package com.ufg.inf.ps.selfservice.infra.commons;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.util.CollectionUtils;

/**
 * Utilitários JPA.
 * @author guilherme
 */
public final class JpaUtils {

  private JpaUtils() {
    super();
  }

  public static <S> void sort(Pageable pageable, CriteriaQuery<?> query, Root<S> root, CriteriaBuilder cb) {
    JpaUtils.sort(pageable.getSort(), query, root, cb);
  }

  public static <S> void sort(Sort sort, CriteriaQuery<?> query, Root<S> root, CriteriaBuilder cb) {
    query.orderBy(QueryUtils.toOrders(sort, root, cb));
  }

  public static String sortSql(Sort sort) {
    if (sort != null && sort.isSorted()) {
      return sort.stream().map(order -> String.format("%s %s", order.getProperty(), order.getDirection().name()))
        .collect(Collectors.joining(", ", "ORDER BY ", StringUtils.EMPTY));
    }
    return StringUtils.EMPTY;
  }

  public static <T> TypedQuery<T> pagination(TypedQuery<T> query, Pageable pageable) {
    if (pageable != null && pageable.isPaged()) {
      query.setFirstResult((int) pageable.getOffset());
      query.setMaxResults(pageable.getPageSize());
    }
    return query;
  }

  public static long count(EntityManager em, Class<?> type) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> query = cb.createQuery(Long.class);
    CriteriaQuery<Long> select = query.select(cb.count(query.from(type)));
    return em.createQuery(select).getSingleResult();
  }

  public static <T> long count(EntityManager em, Class<T> type, Specification<T> specification) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
    Root<T> root = criteria.from(type);
    CriteriaQuery<Long> select = criteria.select(cb.count(root));
    Predicate predicate = specification.toPredicate(root, criteria, cb);
    CriteriaQuery<Long> count = predicate != null ? select.where(predicate) : select;
    return em.createQuery(count).getSingleResult();
  }

  public static <T> Optional<T> optional(TypedQuery<T> typedQuery) {
    return optional(typedQuery.setMaxResults(1).getResultList());
  }

  public static <T> Optional<T> optional(List<T> result) {
    return CollectionUtils.isEmpty(result) ? Optional.empty() : Optional.ofNullable(result.get(0));
  }

  public static <T> Set<T> set(TypedQuery<T> typedQuery) {
    return new HashSet<>(typedQuery.getResultList());
  }

  public static <T> Page<T> page(List<T> list) {
    return new PageImpl<>(list);
  }

  public static <T> Page<T> page(List<T> list, Pageable pageable, long total) {
    return new PageImpl<>(list, pageable, total);
  }

  public static <R> Page<R> page(Pageable pageable, Function<Pageable, Page<R>> page, Supplier<List<R>> list) {
    return optionalPage(pageable).map(page).orElseGet(() -> page(list.get()));
  }

  public static <T> Page<T> page(JpaRepository<T, ?> repository, Pageable pageable) {
    return optionalPage(pageable).map(repository::findAll).orElseGet(() -> page(repository.findAll()));
  }

  public static <T, R> Page<R> page(Specification<T> spec, Pageable pageable,
      Function<Specification<T>, List<R>> list, BiFunction<Specification<T>, Pageable, Page<R>> page) {
    return optionalPage(pageable).map(p -> page.apply(spec, p)).orElseGet(() -> page(list.apply(spec)));
  }

  public static <T> List<T> split(Collection<T> value, Pageable pageable) {
    return splitStream(value, pageable).collect(Collectors.toList());
  }

  public static <T, R> List<R> split(Collection<T> value, Pageable pageable, Function<T, R> mapper) {
    return splitStream(value, pageable).map(mapper).collect(Collectors.toList());
  }

  private static <T> Stream<T> splitStream(Collection<T> value, Pageable pageable) {
    return value.stream().skip(pageable.getOffset()).limit(pageable.getPageSize());
  }

  public static Optional<Pageable> optionalPage(Pageable pageable) {
    return Optional.ofNullable(pageable).filter(Pageable::isPaged);
  }

  public static boolean isPaged(Pageable pageable) {
    return pageable != null && pageable.isPaged();
  }

  public static <T> Page<T> page(List<T> list, Page<?> page) {
    return page(list, page.getPageable(), page.getTotalElements());
  }

}
