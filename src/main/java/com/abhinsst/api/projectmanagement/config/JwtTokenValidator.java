package com.abhinsst.api.projectmanagement.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {
  /** 2* */

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String jwt = request.getHeader(JwtConstant.JWT_HEADER);

    // this is come for jwt - Bearer ... (just skip 7 character)

    if (jwt != null) {
      jwt = jwt.substring(7);
      try {
        SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));
        String authorities = String.valueOf(claims.get("authorities"));

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

        Authentication authentications = new UsernamePasswordAuthenticationToken(email, null, auths);

        SecurityContextHolder.getContext().setAuthentication(authentications);
      } catch (Exception e) {
        throw new BadCredentialsException("invalid token....");

      }
    }

    filterChain.doFilter(request, response);

  }

}
