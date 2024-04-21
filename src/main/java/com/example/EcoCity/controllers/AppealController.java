package com.example.EcoCity.controllers;

import com.example.EcoCity.exceptions.appeals.AppealByIdNotFoundException;
import com.example.EcoCity.exceptions.users.AdminChangeHisAppealStatusException;
import com.example.EcoCity.exceptions.users.UserNotAdminException;
import com.example.EcoCity.models.dto.request.AppealRejectCommentRequest;
import com.example.EcoCity.models.dto.request.AppealRequest;
import com.example.EcoCity.models.dto.request.CreateAppealRequest;
import com.example.EcoCity.models.dto.response.AppealResponse;
import com.example.EcoCity.models.dto.response.ExceptionResponse;
import com.example.EcoCity.services.AppealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.security.Principal;

/**
 * @author Tribushko Danil
 * @since 14.04.2024
 * <p>
 * Контроллер для работы с обращениями
 */
@RestController
@RequestMapping("/appeal")
@Tag(name = "Appeal Controller")
@SecurityRequirement(name = "jwtAuth")
public class AppealController {
    private final AppealService appealService;

    @Autowired
    public AppealController(AppealService appealService) {
        this.appealService = appealService;
    }

    @Operation(summary = "Create Appeal", description = "Create new appeal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appeal created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal type or district or micro district " + " by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "AppealPhoto file empty",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Wrong validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping()
    @Secured("ROLE_USER")
    public ResponseEntity<AppealResponse> create(Principal principal,
                                                 @Valid
                                                 @RequestBody
                                                 CreateAppealRequest request) {
        AppealResponse response = appealService.create(principal.getName(), request);
        return ResponseEntity.created(URI.create("api/appeal/" + response.getId()))
                .body(response);
    }

    @Operation(summary = "Update appeal", description = "Update appeal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeal updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not appeal author",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<AppealResponse> update(Principal principal,
                                                 @PathVariable
                                                 @Min(value = 1, message = "Id can not be less than 1")
                                                 Long id,
                                                 @Valid
                                                 @RequestBody
                                                 AppealRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealService.update(principal.getName(), id, request));

    }

    @Operation(summary = "Delete appeal", description = "Delete appeal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Appeal deleted",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Appeal by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<?> delete(Principal principal,
                                    @PathVariable
                                    @Min(value = 1, message = "Id can not be less tan 1")
                                    Long id) {
        appealService.delete(principal.getName(), id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find appeal by id", description = "Find appeal with the specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeal found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<AppealResponse> findById(@PathVariable
                                                   @Min(value = 1, message = "Id can not be less than 1")
                                                   Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealService.findById(id));
    }

    @Operation(summary = "Add appeal photos", description = "Add photos in appeal with the specified id")
    @PatchMapping(path = "/{id}/photos/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity<AppealResponse> addAppealPhotos(Principal principal,
                                                          @PathVariable
                                                          @Min(value = 1, message = "Id can not be less than 1")
                                                          Long id,
                                                          @RequestPart("files")
                                                          @NotEmpty
                                                          MultipartFile[] files) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealService.addAppealPhotos(principal.getName(), id, files));
    }

    @Operation(summary = "Delete appeal photo", description = "Delete appeal photo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeal photo deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not appeal author",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PatchMapping("/{id}/photos/delete")
    public ResponseEntity<AppealResponse> deleteFile(Principal principal,
                                                     @PathVariable
                                                     @Min(value = 1, message = "Id can not be less than 1")
                                                     Long id,
                                                     @RequestParam("filename")
                                                     String fileName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealService.deleteFile(principal.getName(), id, fileName));
    }

    @Operation(summary = "Delete all appeal photos", description = "Delete all appeal photos from appeal by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All appeal photos deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not appeal author",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PatchMapping("/{id}/photos/delete/all")
    public ResponseEntity<AppealResponse> deleteAllFiles(Principal principal,
                                                         @PathVariable
                                                         @Min(value = 1, message = "Id can not be less than 1")
                                                         Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealService.deleteAllFiles(principal.getName(), id));
    }

    @GetMapping("/{id}/photos/{filename}")
    public ResponseEntity<Resource> downloadAppealPhoto(Principal principal,
                                                        @PathVariable
                                                        @Min(value = 1, message = "Id can not be less than 1")
                                                        Long id,
                                                        @PathVariable(name = "filename")
                                                        String fileName) {
        Resource file = appealService.getFile(principal.getName(), id, fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", file.getFilename()))
                .body(file);
    }

    @Operation(summary = "Accept appeal", description = "Secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeal accepted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Admin can not change his appeal  status",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation exception",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PatchMapping("/{id}/accept")
    public ResponseEntity<AppealResponse> accept(Principal principal,
                                                 @PathVariable
                                                 @Min(value = 1, message = "Id can not be blank")
                                                 Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealService.accept(principal.getName(), id));
    }

    @Operation(summary = "Rejected appeal", description = "Secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appeal rejected",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppealResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appeal by id not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Admin can not change his appeal status",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation exception",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @PatchMapping("/{id}/reject")
    public ResponseEntity<AppealResponse> reject(Principal principal,
                                                 @PathVariable
                                                 @Min(value = 1, message = "Id can not be blank")
                                                 Long id,
                                                 @Valid
                                                 @RequestBody
                                                 AppealRejectCommentRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appealService.reject(principal.getName(), id, request));
    }
}
