package com.example.EcoCity.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 * <p>
 * Dto запос на работу с типом обращения
 */
public class AppealTypeRequest {
    @Schema(description = "AppealType name",
            name = "name",
            type = "string",
            example = "garbage")
    private String name;

    public AppealTypeRequest(String name){
        this.name = name;
    }

    public AppealTypeRequest(){}

    public String getName(){
        return name;
    }
}
