package com.example.EcoCity.services;

import com.example.EcoCity.models.dto.request.SignInRequest;
import com.example.EcoCity.models.dto.request.SignUpRequest;
import com.example.EcoCity.models.dto.response.JwtTokenResponse;
import com.example.EcoCity.models.dto.response.UserResponse;
import jakarta.validation.Valid;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Сервис для авторизации и регистрации пользователей
 */
public interface AuthUserService {
    UserResponse signUp(@Valid
                        SignUpRequest request);

    JwtTokenResponse signIn(@Valid
                            SignInRequest request);
}
