package com.example.EcoCity.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Dto запроса на авторизацию
 */
public class SignInRequest {
    @Schema(description = "User email",
            name = "email",
            type = "string",
            example = "test.email@bk.ru",
            minLength = 7)
    @Email
    @Size(min = 7, message = "Email must contain at least 7 characters")
    private String email;

    @Schema(description = "12345678",
            name = "password",
            type = "string",
            example = "12345678",
            minLength = 8)
    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, message = "Minimum password length should be 8 ")
    private String password;

    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SignInRequest() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
