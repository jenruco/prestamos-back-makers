package com.jenruco.ventas.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jenruco.ventas.usuarios.dto.LoginReq;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private Long jwtExpiration;

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);

    public String generateToken(LoginReq loginReq) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", loginReq.getRol());

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpiration);

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(loginReq.getEmail())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String getRol(String token) {
        Object rol = extractAllClaims(token).get("rol");
        if (rol == null) {
            log.warn("El token no contiene el claim 'rol'");
            return "USER"; // Rol por defecto
        }
        return rol.toString();
    }

    public boolean isValidToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            log.error("Error al validar el token {}", e);
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
