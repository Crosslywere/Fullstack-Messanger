package com.crossly.backend.service.impl;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPIRATION_OFFSET = 1000 * 60 * 60; // 1 hour

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(@NonNull UserDetails user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_OFFSET))
                .signWith(signingKey())
                .compact();
    }

    private Claims extractTokenClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final var claims = extractTokenClaims(token);
        return resolver.apply(claims);
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Date extractIssuedAt(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    public boolean isTokenValid(String token) {
        try {
            var exp = extractExpiration(token);
            var iss = extractIssuedAt(token);
            var now = new Date();
            if (iss.after(now) || exp.before(now))
                return false;
        } catch (JwtException e) {
            return false;
        }
        return true;
    }

}
