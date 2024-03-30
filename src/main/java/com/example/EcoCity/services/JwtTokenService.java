package com.example.EcoCity.services;

import com.example.EcoCity.models.entities.User;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Сервис для работы с jwt токенами
 */
public interface JwtTokenService {
    /**
     * Создание jwt токена
     *
     * @param user сущность пользователя
     * @return jwt токена
     */
    String createToken(User user);

    /**
     * Получение email из jwt токена
     *
     * @param token jwt токен
     * @return email пользователя
     */
    String getEmail(String token);

    /**
     * Проверка токена на валидность
     *
     * @param token jwt токен
     * @param user ущность пользователя
     * @return валидный ли токен
     */
    boolean isTokenValid(String token, User user);
}
