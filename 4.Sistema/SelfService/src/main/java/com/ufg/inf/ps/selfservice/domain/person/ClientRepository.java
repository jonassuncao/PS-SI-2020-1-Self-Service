package com.ufg.inf.ps.selfservice.domain.person;

import com.ufg.inf.ps.selfservice.infra.commons.EntityJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@Repository
public interface ClientRepository extends EntityJpaRepository<Client> {

}
