package com.ufg.inf.ps.selfservice.infra.security;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;

/**
 * @author guilherme.pacheco
 */
public final class UserDetailsChecker {

  public static <T extends Credential> void check(T user) {
    if (!user.isActive()) {
      throw new DisabledException(user.getUsername());
    }
    if (user.isBlocked()) {
      throw new LockedException(user.getUsername());
    }
  }

}
