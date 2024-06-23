package com.saas.starter.security.jwt;

import com.saas.starter.security.UserDetailsCustom;
import com.saas.starter.security.constant.JwtConstant;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
  final JwtProperties jwtProperties;

  private Key getKey() {
    String secretKey = jwtProperties.getSecret();
    if (StringUtils.isBlank(secretKey)) {
      throw new IllegalArgumentException("The secret key for JWT is required!!");
    }
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(StringUtils.rightPad(secretKey, 64, StringUtils.SPACE)));
  }

  public String generateAccessToken(UserDetailsCustom userPrincipal) {
    return Jwts.builder()
              .setSubject(userPrincipal.getUsername())
              .claim(JwtConstant.USER_NAME,  userPrincipal.getUsername())
              .claim(JwtConstant.EMAIL, userPrincipal.getEmail())
              .claim(JwtConstant.ROLE, userPrincipal.getRole())
              .setIssuedAt(new Date())
              .setExpiration(calculateExpireDate(jwtProperties.getExpireTimeAccessToken()))
              .signWith(getKey(), SignatureAlgorithm.HS512)
              .compact();
  }

  public String generateRefreshToken(UserDetailsCustom userPrincipal) {
    return Jwts.builder()
              .setSubject(userPrincipal.getUsername())
              .claim(JwtConstant.USER_NAME,  userPrincipal.getUsername())
              .claim(JwtConstant.EMAIL, userPrincipal.getEmail())
              .claim(JwtConstant.ROLE, userPrincipal.getRole())
              .setIssuedAt(new Date())
              .setExpiration(calculateExpireDate(jwtProperties.getExpireTimeAccessToken()))
              .signWith(getKey(), SignatureAlgorithm.HS512)
              .compact();
  }
  
  private Key key(){
    return Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(jwtProperties.getSecret())
    );
  }

  public String getUsername(String token) {
    Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
    return claims.getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
        Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(authToken);
        return true;
    } catch (MalformedJwtException e) {
        log.error("Invalid JWT token: {} {}", authToken, e.getMessage());
    } catch (ExpiredJwtException e) {
        log.error("JWT token is expired: {} {}", authToken, e.getMessage());
    } catch (UnsupportedJwtException e) {
        log.error("JWT token is unsupported: {} {}", authToken, e.getMessage());
    } catch (IllegalArgumentException e) {
        log.error("JWT claims string is empty: {} {}", authToken, e.getMessage());
    }
    return false;
  }

  private Date calculateExpireDate(long expireTime) {
    return new Date(Date.from(Instant.now()).getTime() + expireTime);
  }
}
