package com.example.EcoCity.services.users;

import com.example.EcoCity.models.dto.request.UpdateUserRequest;
import com.example.EcoCity.models.dto.response.UserResponse;
import jakarta.validation.Valid;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Сервис по работе с пользователями
 */
public interface UserService {
    UserResponse findById(Long id);

    UserResponse update(String email,
            @Valid
            UpdateUserRequest request);

    void delete(String email, Long id);
}
