package com.example.EcoCity.models.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 01.05.2025
 * <p>
 * Dto запроса на работу с мероприятиями
 */
public class EventRequest {
    @Schema(description = "Event name",
            name = "name",
            type = "string",
            example = "Test name")
    private String name;

    @Schema(description = "Event start date",
            name = "start_date")
    @JsonProperty(value = "start_date")
    @Future(message = "StartDate must be future")
    private LocalDateTime startDate;

    @Schema(description = "Event end date",
            name = "end_date")
    @JsonProperty(value = "end_date")
    @Future(message = "EndDate must be future")
    private LocalDateTime endDate;

    @Schema(description = "Event description",
            name = "description",
            type = "string",
            example = "Test event create request description")
    private String description;

    @Schema(description = "Max count participants in event",
            name = "max_participants",
            type = "string",
            example = "2",
            minimum = "2")
    @JsonProperty(value = "max_participants")
    @Min(value = 2, message = "Max count participants can not be less than 2")
    private Integer maxCountParticipants;

    @Schema(description = "Event address",
            name = "address",
            type = "string",
            example = "1 home 1 street")
    @NotBlank(message = "Address can not be blank")
    private String address;


    public EventRequest(String name,
                        LocalDateTime startDate,
                        LocalDateTime endDate,
                        String description,
                        Integer maxCountParticipants,
                        String address) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.maxCountParticipants = maxCountParticipants;
        this.address = address;
    }

    public EventRequest(){}

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaxCountParticipants() {
        return maxCountParticipants;
    }

    public String getAddress() {
        return address;
    }
}
