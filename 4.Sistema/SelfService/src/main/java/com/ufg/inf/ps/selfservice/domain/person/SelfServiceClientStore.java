package com.ufg.inf.ps.selfservice.domain.person;

import com.ufg.inf.ps.selfservice.infra.commons.JpaUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author jonathas.assuncao on 07/12/2020
 * @project SelfService
 */
@Repository
@Transactional(readOnly = true)
public class SelfServiceClientStore {

  private EntityManager entityManager;

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Optional<SelfServiceClient> findByUsername(String username) {
    final String sql = "SELECT s FROM SelfServiceClient s WHERE username = :username";
    final List<SelfServiceClient> result = entityManager.createQuery(sql, SelfServiceClient.class)
        .setParameter("username", username)
        .setMaxResults(1).getResultList();
    return JpaUtils.optional(result);
  }


}
