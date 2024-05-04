package com.example.EcoCity.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 * <p>
 * Dto запроса на работу с типами мероприятий
 */
public class EventTypeRequest {
    @Schema(description = "Event type name",
            name = "name",
            type = "string",
            example = "Subbotnik")
    @NotBlank(message = "Name can not be blank")
    private String name;

    public EventTypeRequest(String name) {
        this.name = name;
    }

    public EventTypeRequest(){}

    public String getName() {
        return name;
    }
}
