package com.ferias.primeiro_projeto_ferias.security;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;

import io.jsonwebtoken.Jwts;

import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtil {
  private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private static final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

  public static String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public static String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public static boolean validateToken(String token) {
    try {
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
      Date expiration = claims.getExpiration();
      return expiration == null || expiration.after(new Date());
    } catch (JwtException e) {
      return false;
    }
  }

  public static boolean validateToken(String token, UserDetails userDetails) {
    try {
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
      String username = claims.getSubject();
      Date expiration = claims.getExpiration();
      return username.equals(userDetails.getUsername()) && (expiration == null || expiration.after(new Date()));
    } catch (JwtException e) {
      return false;
    }
  }
}
