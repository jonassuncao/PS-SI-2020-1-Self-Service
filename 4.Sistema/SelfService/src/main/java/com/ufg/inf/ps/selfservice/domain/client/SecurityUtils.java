package com.ufg.inf.ps.selfservice.domain.client;

import com.ufg.inf.ps.selfservice.infra.commons.DomainUtils;
import com.ufg.inf.ps.selfservice.infra.security.SecurityFunctions;
import com.ufg.inf.ps.selfservice.infra.security.SelfServiceDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author guilherme.pacheco
 */
public final class SecurityUtils {

  private SecurityUtils() {
    super();
  }

  public static SelfServiceDetails getIdentity() {
    return identity().orElseThrow(SecurityFunctions.notAutenthicated());
  }

  public static Optional<SelfServiceDetails> identity() {
    return authentication().flatMap(SecurityUtils::resolveIdentity);
  }


  private static Optional<SelfServiceDetails> resolveIdentity(Authentication authentication) {
    return DomainUtils.ifCast(authentication.getPrincipal(), SelfServiceDetails.class);
  }

  public static Optional<Authentication> authentication() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
  }

  public static Authentication getAuthentication() {
    return authentication().orElseThrow(SecurityFunctions.notAutenthicated());
  }


}
