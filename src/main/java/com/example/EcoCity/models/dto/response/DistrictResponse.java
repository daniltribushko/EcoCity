package com.example.EcoCity.models.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Dto ответа района
 */
public class DistrictResponse {
    @Schema(description = "District name",
            name = "name",
            type = "string",
            example = "New District")
    private String name;

    @Schema(description = "District's micro districts",
            name = "micro district")
    private Set<MicroDistrictResponse> microDistricts;

    public DistrictResponse(String name, Set<MicroDistrictResponse> microDistricts) {
        this.name = name;
        this.microDistricts = microDistricts;
    }

    public DistrictResponse() {
    }

    public String getName() {
        return name;
    }

    public Set<MicroDistrictResponse> getMicroDistricts() {
        return microDistricts;
    }
}
