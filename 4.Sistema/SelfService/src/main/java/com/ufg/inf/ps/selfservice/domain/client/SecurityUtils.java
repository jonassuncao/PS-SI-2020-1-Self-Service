package com.ufg.inf.ps.selfservice.domain.client;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pontta.domain.DomainUtils;
import com.pontta.shared.identity.Identity;
import com.pontta.shared.tenant.TenantId;
import com.pontta.shared.tenant.TenantIdStorage;

/**
 * @author guilherme.pacheco
 */
public final class SecurityUtils {

  private SecurityUtils() {
    super();
  }

  public static PonttaUserDetails getIdentity() {
    return identity().orElseThrow(SecurityFunctions.notAutenthicated());
  }

  public static Optional<PonttaUserDetails> identity() {
    return authentication().flatMap(SecurityUtils::resolveIdentity);
  }

  public static Optional<TenantId> tenantId() {
    return identity().map(Identity::getTenantId);
  }

  /**
   * Obtem o código da tenant do usuário corrente.
   * @return Código da tenant do usuário logado ou <code>null</code> caso não tenha usuário logado
   */
  public static TenantId currentTenantId() {
    return tenantId().or(TenantIdStorage::getTenantId).orElseThrow(SecurityFunctions.notAutenthicated());
  }

  private static Optional<PonttaUserDetails> resolveIdentity(Authentication authentication) {
    return DomainUtils.ifCast(authentication.getPrincipal(), PonttaUserDetails.class);
  }

  public static Optional<Authentication> authentication() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
  }

  public static Authentication getAuthentication() {
    return authentication().orElseThrow(SecurityFunctions.notAutenthicated());
  }

  /**
   * Check if a user is authenticated.
   * @return true if the user is authenticated, false otherwise
   */
  public static boolean isAuthenticated() {
    return authentication().map(SecurityUtils::checkRoles).orElse(false);
  }

  private static boolean checkRoles(Authentication authentication) {
    return authorities(authentication).stream().map(GrantedAuthority::getAuthority)
      .noneMatch(a -> a.equals(SecurityRoles.ANONYMOUS));
  }

  private static Collection<? extends GrantedAuthority> authorities(Authentication authentication) {
    return Objects.requireNonNullElseGet(authentication.getAuthorities(), Collections::emptyList);
  }

  /**
   * If the current user has a specific authority (security role).
   * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
   * @param authority the authority to check
   * @return true if the current user has the authority, false otherwise
   */
  public static boolean containsRole(String authority) {
    return authentication().map(containsAuthority(authority)).orElse(false);
  }

  private static Function<Authentication, Boolean> containsAuthority(String authority) {
    return authentication -> {
      if (authentication.getPrincipal() instanceof UserDetails) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(authority));
      }
      return false;
    };
  }

}
