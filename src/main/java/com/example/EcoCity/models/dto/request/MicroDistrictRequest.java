package com.example.EcoCity.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Dto запроса на создание микрорайона
 */
public class MicroDistrictRequest {
    @Schema(description = "Micro district name",
            name = "name",
            type = "string",
            example = "New Micro district")
    @NotBlank(message = "Name can not be blank")
    private String name;

    public MicroDistrictRequest(String name) {
        this.name = name;
    }

    public MicroDistrictRequest(){}

    public String getName() {
        return name;
    }
}
