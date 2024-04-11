package com.example.EcoCity.controllers;

import com.example.EcoCity.models.dto.request.AppealTypeRequest;
import com.example.EcoCity.models.dto.response.AppealTypeResponse;
import com.example.EcoCity.models.dto.response.AppealsResponse;
import com.example.EcoCity.models.dto.response.ExceptionResponse;
import com.example.EcoCity.services.appeals.AppealTypeService;
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
 * @since 03.04.2024
 * <p>
 * Контроллер для работы с типами обращений
 */
@RestController
@RequestMapping("/appeal/type")
@Tag(name = "Appeal Type Controller")
@SecurityRequirement(name = "jwtAuth")
public class AppealTypeController {
    private final AppealTypeService appealTypeService;

    @Autowired
    public AppealTypeController(AppealTypeService appealTypeService) {
        this.appealTypeService = appealTypeService;
    }

    @Operation(summary = "Create new appeal type", description = "Create new appeal type, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appeal type created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealTypeResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "AppealType already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PostMapping()
    public ResponseEntity<AppealTypeResponse> create(Principal principal,
                                                     @Valid
                                                     @RequestBody
                                                     AppealTypeRequest request) {
        AppealTypeResponse response = appealTypeService.create(principal.getName(), request);
        return ResponseEntity.created(URI.create("api/appeal/type/" + response.getId()))
                .body(response);
    }

    @Operation(summary = "Update appeal type", description = "Update appeal type, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeal type updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealTypeResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin exception",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal type by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<AppealTypeResponse> update(Principal principal,
                                                     @PathVariable
                                                     @Min(value = 1, message = "Id can not be less than 1")
                                                     Integer id,
                                                     @Valid
                                                     @RequestBody
                                                     AppealTypeRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealTypeService.update(principal.getName(), id, request));
    }

    @Operation(summary = "Delete appeal type", description = "Delete appeal type, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Appeal type deleted"),
            @ApiResponse(responseCode = "403", description = "User no admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal type by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Principal principal,
                                    @PathVariable
                                    @Min(value = 1, message = "Id can not be less than 1")
                                    Integer id) {
        appealTypeService.delete(principal.getName(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Find appeal type by id", description = "Find appeal type by id, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeal type found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealTypeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal type by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public ResponseEntity<AppealTypeResponse> findById(@PathVariable
                                                       @Min(value = 1, message = "Id can not be less than 1")
                                                       Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealTypeService.findById(id));
    }

    @Operation(summary = "Find appeals", description = "Find appeals from appeal type by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeals found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealsResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal type by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_USER")
    @GetMapping("/{id}/appeals")
    public ResponseEntity<AppealsResponse> getAppeals(@PathVariable
                                                      @Min(value = 1, message = "Id can not be less than 1")
                                                      Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealTypeService.getAppeals(id));
    }
}
