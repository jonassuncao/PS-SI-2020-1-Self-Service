package com.ufg.inf.ps.selfservice.infra.commons;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@MappedSuperclass
public abstract class Identification implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", updatable = false, nullable = false, unique = true)
  private UUID id;

  protected Identification() {
  }

  protected Identification(UUID id) {
    setId(id);
  }

  protected void initialize() {
    setId(UUID.randomUUID());
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    Optional.ofNullable(id).ifPresent(i -> this.id = i);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return String.format("%s [id=%s]", getClass().getSimpleName(), id);
  }

}
