package com.example.EcoCity.configs;

import com.example.EcoCity.exceptions.jwt.JwtTokenNotValidException;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.JwtTokenService;
import com.example.EcoCity.services.db.DBServiceUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Фильтер для jwt токенов
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final DBServiceUser dbServiceUser;
    private final JwtTokenService jwtTokenService;
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String BEARER_PREFIX = "Bearer";

    @Autowired
    public JwtAuthenticationFilter(DBServiceUser dbServiceUser,
                                   JwtTokenService jwtTokenService) {
        this.dbServiceUser = dbServiceUser;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            doFilter(request, response, filterChain);
            return;
        }
        String token = authHeader.substring(BEARER_PREFIX.length() + 1);
        String email = jwtTokenService.getEmail(token);
        if (!email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = dbServiceUser.findByEmail(email);
            if (jwtTokenService.isTokenValid(token, user)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user,
                                null,
                                user.getAuthorities());
                securityContext.setAuthentication(usernamePasswordAuthenticationToken);
                SecurityContextHolder.setContext(securityContext);
            } else {
                throw new JwtTokenNotValidException();
            }
        }
        doFilter(request, response, filterChain);
    }
}
