package com.ufg.inf.ps.selfservice.infra.commons;

import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface EntityJpaRepository<T> extends JpaRepository<T, UUID> {

  EntityManager em();

  T getOne(Specification<T> specification);

  <I> T getOne(I value, Function<I, Specification<T>> function);

  T getOne(Specification<T> specification, I18nKey key, Object... args);

  CriteriaBuilder criteriaBuilder();

  CriteriaQuery<T> createQuery();

  TypedQuery<T> createQuery(String jpql);

  TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery);

  <E> TypedQuery<E> createQuery(String jpql, Class<E> type);

  <I> Optional<T> findOne(I value, Function<I, Specification<T>> function);

  <I> List<T> findAll(I value, Function<I, Specification<T>> function);

  <R> Page<R> findAll(Specification<T> specification, Class<R> projection, Pageable pageable);

  <R> List<R> findAll(Specification<T> specification, Class<R> projection, Sort sort);

}
