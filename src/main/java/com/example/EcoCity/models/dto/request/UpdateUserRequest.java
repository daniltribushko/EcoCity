package com.example.EcoCity.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Dto запроса на обновление пользователя
 */
public class UpdateUserRequest {
    @Schema(description = "User id",
            name = "id",
            type = "long",
            example = "1",
            minimum = "1")
    @Min(value = 1, message = "Id can not be less than 1")
    private Long id;

    @Schema(description = "User email",
            name = "email",
            type = "string",
            example = "test.email@bk.ru",
            minLength = 7)
    @Email
    @Size(min = 7, message = "Email must contain at least 7 characters")
    private String email;

    @Schema(description = "User surname",
            name = "surname",
            type = "string",
            example = "Ivanov")
    @NotBlank(message = "SurName can not be blank")
    private String surname;

    @Schema(description = "User name",
            name = "user",
            type = "string",
            example = "Ivan")
    @NotBlank(message = "Name can not be blank")
    private String name;

    public UpdateUserRequest(String email, String surname, String name) {
        this.email = email;
        this.surname = surname;
        this.name = name;
    }

    public UpdateUserRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
}
