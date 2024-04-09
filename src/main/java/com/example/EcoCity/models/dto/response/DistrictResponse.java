package com.example.EcoCity.models.dto.response;

import com.example.EcoCity.models.entities.District;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Dto ответа района
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictResponse {
    @Schema(description = "District id",
            name = "id",
            type = "integer",
            example = "1")
    private Integer id;
    @Schema(description = "District name",
            name = "name",
            type = "string",
            example = "New District")
    private String name;

    @Schema(description = "District's micro districts",
            name = "micro district")
    private Set<MicroDistrictResponse> microDistricts;

    public DistrictResponse(Integer id, String name, Set<MicroDistrictResponse> microDistricts) {
        this.id = id;
        this.name = name;
        this.microDistricts = microDistricts;
    }

    public DistrictResponse(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public DistrictResponse() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<MicroDistrictResponse> getMicroDistricts() {
        return microDistricts;
    }

    public static DistrictResponse mapFromEntity(District district) {
        return new DistrictResponse(district.getId(),
                district.getName(),
                district.getMicroDistricts()
                        .stream()
                        .map(MicroDistrictResponse::mapFromEntity)
                        .collect(Collectors.toSet()));
    }
}
