package com.ufg.inf.ps.selfservice.infra.response;

import com.ufg.inf.ps.selfservice.infra.commons.DomainUtils;
import com.ufg.inf.ps.selfservice.infra.commons.Identification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author jonathas.assuncao on 10/12/2020
 * @project SelfService
 */
public final class ResponseFactory {

  private static final Map<AlertMessageType, HttpStatus> TYPES = new EnumMap<>(AlertMessageType.class);
  private static final HeaderMessageBuilder HEADER_BUILDER = new HeaderMessageBuilder();

  static {
    TYPES.put(AlertMessageType.CREATED, HttpStatus.CREATED);
    TYPES.put(AlertMessageType.UPDATED, HttpStatus.OK);
    TYPES.put(AlertMessageType.DELETED, HttpStatus.OK);
  }

  public static <T> ResponseEntity<T> notFound() {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public static ResponseEntity<Object> created() {
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  public static <T> ResponseEntity<T> created(T body) {
    return new ResponseEntity<>(body, HttpStatus.CREATED);
  }

  public static ResponseEntity<Object> created(String entityName, Identification body) {
    return response(AlertMessageType.CREATED, entityName, body.getId());
  }

  public static ResponseEntity<Object> created(String entityName, Object param) {
    return response(AlertMessageType.CREATED, entityName, param);
  }

  public static <T extends Identification> ResponseEntity<T> updated(String entityName, Optional<T> body) {
    return body.map(b -> update(entityName, b)).orElseGet(ResponseFactory::notFound);
  }

  private static <T extends Identification> ResponseEntity<T> update(String entityName, T body) {
    return response(AlertMessageType.UPDATED, entityName, body);
  }

  public static <T> ResponseEntity<T> response(AlertMessageType type, String entityName, T body) {
    return bodyBuilder(type, entityName, body).body(body);
  }

  public static <T> BodyBuilder bodyBuilder(AlertMessageType type, String entityName, T body) {
    HttpHeaders headers = HEADER_BUILDER.createAlert(type.format(entityName), extractId(body));
    return bodyBuilder(type, headers);
  }

  private static BodyBuilder bodyBuilder(AlertMessageType type, HttpHeaders headers) {
    HttpStatus status = TYPES.getOrDefault(type, HttpStatus.OK);
    return ResponseEntity.status(status).headers(headers);
  }

  private static String extractId(Object body) {
    return DomainUtils.ifCast(body, Identification.class).map(Identification::getId).map(String::valueOf)
        .orElse(String.valueOf(body));
  }


}
