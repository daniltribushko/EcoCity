package com.example.EcoCity.services.iml;

import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Реализация сервиса по работе с jwt токенами
 */
@Service
public class JwtTokenServiceImp implements JwtTokenService {
    @Value("${token.secret}")
    private String secret;

    @Override
    public String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        claims.put("roles", roles);
        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime() + 1000 * 3600 * 24);
        return Jwts
                .builder()
                .subject(user.getEmail())
                .claims(claims)
                .signWith(getKey())
                .issuedAt(issueDate)
                .expiration(expiredDate)
                .compact();
    }

    @Override
    public String getEmail(String token) {
        Claims claims = getAllClaims(token);
        return claims.getSubject();
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        Claims claims = getAllClaims(token);
        Date expirationDate = claims.getExpiration();
        return Objects.equals(user.getEmail(), claims.getSubject()) && expirationDate.after(new Date());
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
