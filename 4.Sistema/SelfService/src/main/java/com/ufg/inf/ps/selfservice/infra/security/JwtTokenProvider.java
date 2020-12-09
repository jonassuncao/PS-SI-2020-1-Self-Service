package com.ufg.inf.ps.selfservice.infra.security;

import com.ufg.inf.ps.selfservice.infra.commons.DomainUtils;
import com.ufg.inf.ps.selfservice.infra.exception.SelfServiceException;
import com.ufg.inf.ps.selfservice.infra.intercionalization.I18nCommon;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        .setSubject(usernameId(authentication))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .claim(JwtConstants.AUTHORITIES_KEY, authorities(authentication))
        .setExpiration(expirationDate())
        .compact();
  }

  private String usernameId(Authentication authentication) {
   return DomainUtils.ifCast(authentication.getPrincipal(), SelfServiceDetails.class).map(SelfServiceDetails::getUserId)
       .map(String::valueOf).orElseThrow(SelfServiceException.supplier(I18nCommon.ERROR_INTERNAL));
  }

  private Date expirationDate() {
    final LocalDateTime now = LocalDateTime.now().plusMinutes(EXPIRATION_LIMIT_MINUTE);
    return Date.from(Instant.from(now.atZone(ZoneId.systemDefault())));
  }

  private String authorities(Authentication authentication) {
    return authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
  }
}
