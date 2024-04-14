package com.example.EcoCity.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 * <p>
 * Dto запроса на создание обращения
 */
public class CreateAppealRequest {

    @Schema(description = "Appeal text",
            name = "text",
            type = "string",
            example = "New text",
            maxLength = 1000)
    @NotBlank(message = "Text can not be blank")
    @Size(max = 1000, message = "Maximum text size 1000 characters")
    private String text;

    @Schema(description = "Appeal address",
            name = "address",
            type = "string",
            example = "Home 1 street 1 kv 1")
    @NotBlank(message = "Address can not be blank")
    private String address;

    @Schema(description = "Appeal's appeal type id",
            name = "appealTypeId",
            type = "integer",
            example = "1",
            minimum = "1")
    @Min(value = 1, message = "Appeal id can not be less than 1")
    private Integer appealTypeId;

    @Schema(description = "Appeal's district id",
            name = "districtId",
            type = "integer",
            example = "1",
            minimum = "1")
    @Min(value = 1, message = "District id can not be less than 1")
    private Integer districtId;

    @Schema(description = "Appeal's micro district id",
            name = "microDistrictId",
            type = "integer",
            example = "1",
            minimum = "1")
    @Min(value = 1, message = "Micro district id can not be less than 1")
    private Integer microDistrictId;

    public CreateAppealRequest(String text,
                               String address,
                               Integer appealTypeId,
                               Integer districtId,
                               Integer microDistrictId) {
        this.text = text;
        this.address = address;
        this.appealTypeId = appealTypeId;
        this.districtId = districtId;
        this.microDistrictId = microDistrictId;
    }

    public CreateAppealRequest() {
    }


    public String getText() {
        return text;
    }

    public Integer getAppealTypeId() {
        return appealTypeId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public Integer getMicroDistrictId() {
        return microDistrictId;
    }

    public String getAddress() {
        return address;
    }
}
