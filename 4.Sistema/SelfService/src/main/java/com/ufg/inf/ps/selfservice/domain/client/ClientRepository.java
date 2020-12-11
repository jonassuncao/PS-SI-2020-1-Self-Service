package com.ufg.inf.ps.selfservice.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao on 07/12/2020
 * @project SelfService
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

  Optional<? extends Client> findByUsername(String username);

  boolean existsByUsernameIgnoreCase(String username);
}
