package com.ufg.inf.ps.selfservice.infra.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jonathas.assuncao on 04/12/2020
 * @project SelfService
 */
@MappedSuperclass
public class Identification implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", updatable = false)
  private UUID id;

  @JsonProperty
  public UUID getId() {
    return id;
  }

  protected final void initialize() {
    id = UUID.randomUUID();
  }

  protected void setId(UUID id) {
    this.id = Validate.notNull(id, "Invalid DomainEntity ID");
  }

  public boolean isSameId(@Nullable UUID id) {
    return Objects.equals(this.id, id);
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
