package com.ufg.inf.ps.selfservice.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao on 07/12/2020
 * @project SelfService
 */
@Repository
public interface SelfServiceClientRepository extends JpaRepository<SelfServiceClient, UUID> {

  Optional<? extends SelfServiceClient> findByUsername(String username);

}
