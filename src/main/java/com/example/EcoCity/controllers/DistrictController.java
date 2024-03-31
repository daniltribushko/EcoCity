package com.example.EcoCity.controllers;

import com.example.EcoCity.models.dto.request.DistrictRequest;
import com.example.EcoCity.models.dto.response.DistrictResponse;
import com.example.EcoCity.models.dto.response.ExceptionResponse;
import com.example.EcoCity.services.districts.DistrictService;
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
 * @since 31.03.2024
 * <p>
 * Контроллер для работы с районами
 */
@RestController
@RequestMapping("/districts")
@Tag(name = "District Controller")
@SecurityRequirement(name = "jwtAuth")
public class DistrictController {
    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @Operation(summary = "Create new district", description = "Create new district, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "District created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DistrictResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "District already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PostMapping()
    public ResponseEntity<DistrictResponse> create(Principal principal,
                                                   @Valid
                                                   @RequestBody
                                                   DistrictRequest request) {
        DistrictResponse response = districtService.create(principal.getName(), request);
        return ResponseEntity.created(URI.create("api/districs/" + response.getId()))
                .body(response);
    }

    @Operation(summary = "Update district", description = "Update micro district, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "District updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DistrictResponse.class))),
            @ApiResponse(responseCode = "404", description = "District not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<DistrictResponse> update(Principal principal,
                                                   @PathVariable
                                                   @Min(value = 1, message = "Id can not be less than 1")
                                                   Integer id,
                                                   @Valid
                                                   @RequestBody
                                                   DistrictRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(districtService.update(principal.getName(), id, request));
    }

    @Operation(summary = "Delete district", description = "Delete district, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "District deleted"),
            @ApiResponse(responseCode = "404", description = "District not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Principal principal,
                                    @PathVariable
                                    @Min(value = 1, message = "Id can not be less than 1")
                                    Integer id) {
        districtService.delete(principal.getName(), id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find by id", description = "Find district by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "District found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DistrictResponse.class))),
            @ApiResponse(responseCode = "404", description = "District not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<DistrictResponse> findById(@PathVariable
                                                     @Min(value = 1, message = "Id can not be less than 1")
                                                     Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(districtService.findById(id));
    }
}
