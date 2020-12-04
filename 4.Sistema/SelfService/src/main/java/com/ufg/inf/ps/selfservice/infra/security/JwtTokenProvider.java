package com.ufg.inf.ps.selfservice.infra.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author jonathas.assuncao on 03/12/2020
 * @project SelfService
 */
@Component
public class JwtTokenProvider {

  static final String SECRET_KEY = String.valueOf(UUID.randomUUID());
  private static final long EXPIRATION_LIMIT_MINUTE = 180;

  public String createToken(Authentication authentication) {
    return Jwts.builder()
        .setSubject(authentication.getName())
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .claim(JwtConstants.AUTHORITIES_KEY, authorities(authentication))
        .setExpiration(expirationDate())
        .compact();
  }

  private Date expirationDate() {
    final Instant now = Instant.now().minusMillis(EXPIRATION_LIMIT_MINUTE);
    return Date.from(now);
  }

  private String authorities(Authentication authentication) {
    return authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
  }
}
