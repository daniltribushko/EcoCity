package com.example.EcoCity.controllers;

import com.example.EcoCity.models.dto.request.CreateMicroDistrictRequest;
import com.example.EcoCity.models.dto.request.MicroDistrictRequest;
import com.example.EcoCity.models.dto.response.DistrictResponse;
import com.example.EcoCity.models.dto.response.ExceptionResponse;
import com.example.EcoCity.models.dto.response.MicroDistrictResponse;
import com.example.EcoCity.services.MicroDistrictService;
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
 * Контроллер для работы с микрорайонами
 */
@RestController
@RequestMapping("/microdistricts")
@SecurityRequirement(name = "jwtAuth")
@Tag(name = "Micro district Controller")
public class MicroDistrictController {
    private final MicroDistrictService microDistrictService;

    @Autowired
    public MicroDistrictController(MicroDistrictService microDistrictService) {
        this.microDistrictService = microDistrictService;
    }

    @Operation(summary = "Create new micro district", description = "Create new micro district in" +
            " district, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Micro district created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DistrictResponse.class))),
            @ApiResponse(responseCode = "404", description = "District not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Micro district already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PostMapping()
    public ResponseEntity<DistrictResponse> create(Principal principal,
                                                   @Valid
                                                   @RequestBody
                                                   CreateMicroDistrictRequest request) {
        DistrictResponse response = microDistrictService.create(principal.getName(), request);
        return ResponseEntity.created(URI.create("api/microdistricts/" + response.getId()))
                .body(response);
    }

    @Operation(summary = "Update micro district", description = "Update micro district, " +
            "secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Micro district updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Micro district not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<MicroDistrictResponse> update(Principal principal,
                                                        @PathVariable
                                                        @Min(value = 1, message = "Id can not be less than 1")
                                                        Integer id,
                                                        @Valid
                                                        @RequestBody
                                                        MicroDistrictRequest request) {
        MicroDistrictResponse response = microDistrictService.update(principal.getName(), id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete micro district", description = "Delete micro district by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Micro district deleted",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Micro district not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Principal principal,
                                    @PathVariable
                                    @Min(value = 1, message = "Id can not be less than 1")
                                    Integer id) {
        microDistrictService.delete(principal.getName(), id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Change district", description = "Change district of micro district")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "District changed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DistrictResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "District or micro district not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PatchMapping("/{id}/district/{districtId}/change")
    public ResponseEntity<DistrictResponse> changeDistrict(Principal principal,
                                                           @PathVariable
                                                           @Min(value = 1, message = "Id can not be less than 1")
                                                           Integer id,
                                                           @PathVariable
                                                           @Min(value = 1, message = "Id can not be less than 1")
                                                           Integer districtId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(microDistrictService.changeDistrict(principal.getName(), id, districtId));
    }

    @Operation(summary = "Find by id", description = "Find district by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Micro district found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MicroDistrictResponse.class))),
            @ApiResponse(responseCode = "404", description = "Micro district not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MicroDistrictResponse> findById(@PathVariable
                                                          @Min(value = 1, message = "Id can not be less than 1")
                                                          Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(microDistrictService.findById(id));
    }
}
