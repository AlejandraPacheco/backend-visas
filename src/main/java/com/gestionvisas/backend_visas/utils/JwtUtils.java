package com.gestionvisas.backend_visas.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    private static final String JWT_SECRET = "ElMejorEquipoDelMundoYDeLaHistoriaEsRealMadrid"; // tu clave secreta
    //private static final long JWT_EXPIRATION_MS = 86400000; // 1 día

    public static String generateToken(String username, String role) {
        long expiration = 86400000; // 1 día
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // <-- aquí agregas el rol
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes())
                .compact();
    }
}
