package com.example.EcoCity.models.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Tribushko Danil
 * @since 31.03.2024
 * <p>
 * Dto запроса на создание микрорайона
 */
public class CreateMicroDistrictRequest {
    @Schema(description = "Id of the district in which the microdistrict will be created",
            name = "district id",
            type = "integer",
            example = "1")
    @JsonProperty(value = "district id")
    @Min(value = 1, message = "Id can not be less than 1")
    private Integer districtId;

    @Schema(description = "Microdistrict name",
            name = "name",
            type = "string",
            example = "New Micro district")
    @NotBlank(message = "Name can not be blank")
    private String name;

    public CreateMicroDistrictRequest(Integer districtId, String name) {
        this.districtId = districtId;
        this.name = name;
    }

    public CreateMicroDistrictRequest() {
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}