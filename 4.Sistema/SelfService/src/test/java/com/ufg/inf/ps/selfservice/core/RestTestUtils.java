package com.ufg.inf.ps.selfservice.core;

import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static com.ufg.inf.ps.selfservice.infra.response.HeaderMessageBuilder.X_ALERT;
import static com.ufg.inf.ps.selfservice.infra.response.HeaderMessageBuilder.X_PARAMS;

/**
 * @author jonathas.assuncao on 11/12/2020
 * @project SelfService
 */
public final class RestTestUtils {

  private RestTestUtils() {
    super();
  }

  public static Object extractHeaderParams(ResultActions actions) {
    return actions.andReturn().getResponse().getHeaderValue(X_PARAMS);
  }

  public static UUID extractUuidHeaderParams(ResultActions actions) {
    return UUID.fromString(String.valueOf(extractHeaderParams(actions)));
  }

  public static Object extractHeaderAlert(ResultActions actions) {
    return actions.andReturn().getResponse().getHeaderValue(X_ALERT);
  }
}
