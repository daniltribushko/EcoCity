package com.example.EcoCity.services.users;

import com.example.EcoCity.models.dto.request.UpdateUserRequest;
import com.example.EcoCity.models.dto.response.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Сервис по работе с пользователями
 */
public interface UserService {
    /**
     * Поиск пользователя по указанному идентификатору
     *
     * @param id идентификатор пользователя
     * @return dto ответа пользавателя
     */
    UserResponse findById(@Min(value = 1, message = "Id can not be less than 1")
                          Long id);

    /**
     * Обновление пользователя
     *
     * @param email   электронный адрес пользователя
     * @param request запрос на обновление пользователя
     */
    UserResponse update(@Email
                        @Size(min = 7, message = "Email must be contain 7 characters")
                        String email,
                        @Valid
                        UpdateUserRequest request);

    /**
     * Удаление пользователя
     *
     * @param email электронный адрес пользователя
     * @param id    идентификатор пользователя
     */
    void delete(@Email
                @Size(min = 7, message = "Email must be contain 7 characters")
                String email,
                Long id);
}
