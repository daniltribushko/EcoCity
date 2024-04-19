package com.example.EcoCity.services.imp;

import com.example.EcoCity.models.entities.Role;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.services.JwtTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 19.04.2024
 * <p>
 * Класс для тестирование сервиса по работе с jwt токенами
 */
@SpringBootTest
class JwtTokenServiceTest {
    private JwtTokenService jwtTokenService;
    private String token;

    @Autowired
    JwtTokenServiceTest(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @BeforeEach
    void createToken() {
        User user = new User()
                .builder()
                .email("Test user")
                .roles(Set.of(new Role("Role 1")))
                .build();
        token = jwtTokenService.createToken(user);
    }

    @Test
    @Order(1)
    void createTokenTest() {
        Assertions.assertNotNull(token);
    }

    @Test
    @Order(2)
    void getEmailTest() {
        Assertions.assertEquals("Test user", jwtTokenService.getEmail(token));
    }

    @Test
    @Order(3)
    void tokenValidTest() {
        User user = new User()
                .builder()
                .email("Test user")
                .roles(Set.of(new Role("Role 1")))
                .build();
        Assertions.assertTrue(jwtTokenService.isTokenValid(token, user));
    }

    @Test
    @Order(4)
    void tokenNotValidTest() {
        User user = new User()
                .builder()
                .email("Test user 2")
                .roles(Set.of(new Role("Role 1")))
                .build();
        Assertions.assertFalse(jwtTokenService.isTokenValid(token, user));
    }
}
