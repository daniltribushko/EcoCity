package com.example.EcoCity.controllers;

import com.example.EcoCity.models.dto.request.CreateEventRequest;
import com.example.EcoCity.models.dto.request.EventRequest;
import com.example.EcoCity.models.dto.response.EventResponse;
import com.example.EcoCity.models.dto.response.ExceptionResponse;
import com.example.EcoCity.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

/**
 * @author Tribushko Danil
 * @since 03.05.2024
 * <p>
 * Контроллер для работы с мероприятиями
 */
@RestController
@RequestMapping("/events")
@Tag(name = "Event Controller")
@SecurityRequirement(name = "jwtAuth")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Create Event", description = "Secured by organization and admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not organization",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Event with name already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Start date event after end date event date " +
                    "or conversely", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "District or micro district or event type by" +
                    " id not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    @Secured({"USER_ADMIN", "USER_ORGANIZATION"})
    public ResponseEntity<EventResponse> create(Principal principal,
                                                @Valid
                                                @RequestBody
                                                CreateEventRequest request) {
        EventResponse response = eventService.create(principal.getName(), request);
        return ResponseEntity.created(URI.create("api/events/" + response.getId()))
                .body(response);
    }

    @Operation(summary = "Update event", description = "Secured by organization and admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not event creator",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Start date event after end date event date " +
                    "or conversely or participants more than max count",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_ORGANIZATION"})
    public ResponseEntity<EventResponse> update(Principal principal,
                                                @PathVariable
                                                @Min(value = 1, message = "Id can not be less than 1")
                                                Long id,
                                                @Valid
                                                @RequestBody
                                                EventRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventService.update(principal.getName(), id, request));
    }

    @Operation(summary = "Delete event", description = "Secured by organization and admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event delete"),
            @ApiResponse(responseCode = "404", description = "Event by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not event creator",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_ORGANIZATION"})
    public ResponseEntity<?> delete(Principal principal,
                                    @PathVariable
                                    @Min(value = 1, message = "Id can not be less than 1")
                                    Long id) {
        eventService.delete(principal.getName(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Find event by id", description = "Find event by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<EventResponse> findById(@PathVariable
                                                  @Min(value = 1, message = "Id can not be less than 1")
                                                  Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventService.findById(id));
    }

    @Operation(summary = "Join in event", description = "join user in event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User joined in event",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventResponse.class))),
            @ApiResponse(responseCode = "404", description = "User by email or event by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Event participants if full or user already participant",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PatchMapping("/{id}/join")
    @Secured("ROLE_USER")
    public ResponseEntity<EventResponse> join(Principal principal,
                                              @PathVariable
                                              @Min(value = 1, message = "Id can not be less than 1")
                                              Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventService.join(principal.getName(), id));
    }

    @Operation(summary = "Leave from event", description = "leave user from event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User leaved event",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventResponse.class))),
            @ApiResponse(responseCode = "404", description = "User by email or event by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PatchMapping("/{id}/leave")
    public ResponseEntity<EventResponse> leave(Principal principal,
                                               @PathVariable
                                               @Min(value = 1, message = "Id can not be less than 1")
                                               Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventService.leave(principal.getName(), id));
    }
}
