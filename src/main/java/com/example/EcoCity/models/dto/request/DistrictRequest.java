package com.example.EcoCity.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Dto запроса на создание пользователя
 */
public class DistrictRequest {
    @Schema(description = "District name",
            name = "name",
            type = "string",
            example = "New District")
    @NotBlank(message = "Name can not be blank")
    private String name;

    public DistrictRequest(String name) {
        this.name = name;
    }

    public DistrictRequest() {
    }

    public String getName() {
        return name;
    }
}
