package com.example.EcoCity.models.dto.response;

import com.example.EcoCity.models.entities.MicroDistrict;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Dto ответа микро района
 */
public class MicroDistrictResponse {
    @Schema(description = "Micro district id",
            name = "id",
            type = "long",
            example = "1")
    private Integer id;

    @Schema(description = "Micro district name",
            name = "name",
            type = "string",
            example = "New Micro district")
    private String name;

    public MicroDistrictResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public MicroDistrictResponse() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static MicroDistrictResponse mapFromEntity(MicroDistrict microDistrict){
        return new MicroDistrictResponse(microDistrict.getId(), microDistrict.getName());
    }
}
