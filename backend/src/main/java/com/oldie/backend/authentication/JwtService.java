package com.oldie.backend.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import jakarta.annotation.PostConstruct;

import java.util.Date;
import javax.crypto.SecretKey;
import java.util.UUID;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UUID userID, String email, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        String id = userID.toString();
        return Jwts.builder()
                .subject(id)
                .claim("email", email)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(this.key)
                .compact();
    }
}