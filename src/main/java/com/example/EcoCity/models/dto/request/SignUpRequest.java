package com.example.EcoCity.models.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 *
 * Dto запроса на регистрацию пользователя
 */
public class SignUpRequest {
    @Schema(
            description = "User email",
            name = "email",
            type = "string",
            example = "test.email@bk.ru",
            minLength = 7)
    @Email
    @Size(min = 7, message = "Email must contain at least 7 characters")
    private String email;

    @Schema(
            description = "User surname",
            name = "surname",
            type = "string",
            example = "Ivanov")
    @NotBlank(message = "SurName can not be blank")
    private String surname;

    @Schema(
            description = "User name",
            name = "name",
            type = "string",
            example = "Ivan")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Schema(
            description = "User password",
            name = "password",
            type = "string",
            example = "12345678",
            minLength = 8)
    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, message = "Minimum password length should be 8 ")
    private String password;

    @Schema(
            description = "User confirm password",
            name = "confirm password",
            type = "string",
            example = "12345678",
            minLength = 8)
    @JsonProperty(value = "confirm password")
    @NotBlank(message = "Confirm password can not be blank")
    @Size(min = 8, message = "Minimum confirm password length should be 8 ")
    private String confirmPassword;

    public SignUpRequest(String email, String surname, String name, String password, String confirmPassword) {
        this.email = email;
        this.surname = surname;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public SignUpRequest() {
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

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

}
