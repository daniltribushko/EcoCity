package com.example.EcoCity.models.dto.response;

import com.example.EcoCity.models.entities.AppealType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Dto ответа типа обращения
 */
public class AppealTypeResponse {
    @Schema(description = "AppealType id",
            name = "id",
            type = "integer",
            example = "1")
    private Integer id;

    @Schema(description = "AppealType name",
            name = "name",
            type = "string",
            example = "garbage")
    private String name;

    public AppealTypeResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AppealTypeResponse(){}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AppealTypeResponse mapFromEntity(AppealType appealType){
        return new AppealTypeResponse(appealType.getId(), appealType.getName());
    }
}
