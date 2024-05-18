package com.abhinsst.api.projectmanagement.config;

import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
  static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());

  public static String generateToken(Authentication auth) {
    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
    String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 8640000))
        .claim("email", auth.getName())
        .signWith(key)
        .compact();

    return jwt;
  }

  public static String getEmailFromToken(String jwt) {
    Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

    String email = String.valueOf(claims.get("email"));
    return email;
  }

}
