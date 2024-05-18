package com.abhinsst.api.projectmanagement.config;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class JwtProvider {
  static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());

}
