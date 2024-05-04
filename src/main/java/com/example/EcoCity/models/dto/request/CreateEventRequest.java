package com.example.EcoCity.models.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 * <p>
 * Dto запроса на создание мероприятия
 */
public class CreateEventRequest {
    @Schema(description = "Event name",
            name = "name",
            type = "string",
            example = "Test name")
    @NotBlank(message = "Name can not be blank")
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
    @NotBlank(message = "Description can not be blank")
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

    @Schema(description = "Id of event type",
            name = "event_type",
            type = "long",
            example = "1",
            minimum = "1")
    @JsonProperty(value = "event_type")
    @Min(value = 1, message = "Event type id can not be less than 1")
    private Integer eventTypeId;

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

    public CreateEventRequest(String name,
                              LocalDateTime startDate,
                              LocalDateTime endDate,
                              String description,
                              Integer maxCountParticipants,
                              String address,
                              Integer eventTypeId,
                              Integer districtId,
                              Integer microDistrictId) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.maxCountParticipants = maxCountParticipants;
        this.address = address;
        this.eventTypeId = eventTypeId;
        this.districtId = districtId;
        this.microDistrictId = microDistrictId;
    }

    public CreateEventRequest() {
    }

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

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public Integer getMicroDistrictId() {
        return microDistrictId;
    }
}
