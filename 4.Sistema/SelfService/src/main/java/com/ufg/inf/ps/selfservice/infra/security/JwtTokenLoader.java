package com.ufg.inf.ps.selfservice.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
@Component
public class JwtTokenLoader {

  public Authentication authentication(String token) {
    Claims claims = tokenClaims(token);
    Credential user = userIdentity(claims);
    Collection<GrantedAuthority> authorities = authorities(claims);
    SelfServiceDetails userDetails = new SelfServiceDetails(user, authorities);
    return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
  }

  private Claims tokenClaims(String token) {
    return Jwts.parser().setSigningKey(JwtTokenProvider.SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private Collection<GrantedAuthority> authorities(Claims claims) {
    String authorities = String.valueOf(claims.get(JwtConstants.AUTHORITIES_KEY));
    return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
  }

  private Credential userIdentity(Claims claims) {
    Credential user = user(claims.getSubject());
    UserDetailsChecker.check(user);
    return user;
  }

  private Credential user(String subject) {
//    return userProvider.findById(UUID.fromString(subject)).orElseThrow(SecurityFunctions.notAutenthicated());
    return null;
  }
}
