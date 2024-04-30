package com.example.EcoCity.models.dto.response;

import com.example.EcoCity.models.entities.EventType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 * <p>
 * Dto запроса типа мероприятия
 */
public class EventTypeResponse {
    @Schema(description = "Event type id",
            name = "id",
            type = "integer",
            example = "1")
    private Integer id;
    @Schema(description = "Event type name",
            name = "name",
            type = "string",
            example = "Subbotnik")
    private String name;

    public EventTypeResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public EventTypeResponse() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EventTypeResponse mapFromEntity(EventType eventType){
        return new EventTypeResponse(eventType.getId(), eventType.getName());
    }
}
