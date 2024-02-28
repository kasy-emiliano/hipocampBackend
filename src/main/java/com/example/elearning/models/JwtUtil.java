/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elearning.models;

/**
 *
 * @author Cedrick
 */
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "votreSecretKey"; // Remplacez par une clé secrète réelle
    private static final long EXPIRATION_TIME = 864_000_000; // 10 jours en millisecondes

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            // Gérer l'exception si le token a expiré
            return e.getClaims();
        }
    }

    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date());
    }
     public Apprenant extractApprenant(String token) {
        Claims claims = extractClaims(token);
        Apprenant apprenant = new Apprenant();
        // Vous devrez adapter cette partie en fonction de vos revendications et de votre classe Apprenant
        apprenant.setIdApprenant((int) claims.get("idApprenant"));
        apprenant.setNom(claims.get("nom").toString());
        // Ajoutez d'autres propriétés au besoin
        return apprenant;
    }
}

