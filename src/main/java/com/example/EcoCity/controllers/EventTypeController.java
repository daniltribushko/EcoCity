package com.example.EcoCity.controllers;

import com.example.EcoCity.models.dto.request.EventTypeRequest;
import com.example.EcoCity.models.dto.response.EventTypeResponse;
import com.example.EcoCity.models.dto.response.ExceptionResponse;
import com.example.EcoCity.services.EventTypeService;
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
 * @since 30.04.2024
 * <p>
 * Контроллер для работы с типами мероприятий
 */
@RestController
@Tag(name = "Event Type Controller")
@SecurityRequirement(name = "jwtAuth")
@RequestMapping("/event/type")
public class EventTypeController {
    private final EventTypeService eventTypeService;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @Operation(summary = "Create new event type", description = "Secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event type created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventTypeResponse.class))),
            @ApiResponse(responseCode = "409", description = "Event type already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping()
    @Secured("ROLE_USER")
    public ResponseEntity<EventTypeResponse> create(Principal principal,
                                                    @Valid
                                                    @RequestBody
                                                    EventTypeRequest request) {
        EventTypeResponse response = eventTypeService.create(principal.getName(), request);
        return ResponseEntity.created(URI.create("api/event/type/" + response.getId()))
                .body(response);
    }

    @Operation(summary = "Update event type", description = "Secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event type updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventTypeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event type by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<EventTypeResponse> update(Principal principal,
                                                    @PathVariable
                                                    @Min(value = 1, message = "Id can not be less than 1")
                                                    Integer id,
                                                    @Valid
                                                    @RequestBody
                                                    EventTypeRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventTypeService.update(principal.getName(), id, request));
    }

    @Operation(summary = "Find event type by id", description = "Find event type by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event type found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventTypeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event type by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventTypeResponse> findById(@PathVariable
                                                      @Min(value = 1, message = "Id can not be less than 1")
                                                      Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventTypeService.findById(id));
    }

    @Operation(summary = "Delete event type", description = "Secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event type deleted"),
            @ApiResponse(responseCode = "404", description = "Event type by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventTypeResponse.class)))
    })
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> delete(Principal principal,
                                    @PathVariable
                                    @Min(value = 1, message = "Id can not be less than 1")
                                    Integer id) {
        eventTypeService.delete(principal.getName(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
