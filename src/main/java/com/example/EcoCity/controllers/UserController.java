package com.example.EcoCity.controllers;

import com.example.EcoCity.models.dto.response.ExceptionResponse;
import com.example.EcoCity.models.dto.response.UsersResponse;
import com.example.EcoCity.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * @author Tribusko Danil
 * @since 22.04.2024
 * <p>
 * Контроллер для работы с пользователями
 */
@Tag(name = "User Controller")
@SecurityRequirement(name = "jwtAuth")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Find all", description = "Find all users, secured by admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "403", description = "Users not admin",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<UsersResponse> findAll(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(principal.getName()));
    }

    @Operation(summary = "Find all with pagination", description = "Find all users with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "400", description = "Wrong record state",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @Secured("ROLE_USER")
    @GetMapping()
    public ResponseEntity<UsersResponse> findAll(
            @Schema(description = "Users page number",
                    defaultValue = "0",
                    name = "page",
                    type = "integer",
                    example = "0")
            @RequestParam(required = false,
                    defaultValue = "0")
            @Min(value = 0, message = "Page can not be less than 0")
            Integer page,
            @Schema(description = "Users per page count",
                    defaultValue = "10",
                    name = "per_page",
                    type = "integer",
                    example = "2")
            @RequestParam(required = false, defaultValue = "10", name = "per_page")
            @Min(value = 1, message = "per page can not be less than 1")
            Integer perPage,
            @Schema(description = "Users record state",
                    name = "recordState",
                    type = "string",
                    example = "ACTIVE")
            @RequestParam(required = false)
            String recordState,
            @Schema(description = "Users creation date",
                    name = "createDate",
                    type = "string")
            @RequestParam(required = false)
            LocalDateTime createDate,
            @Schema(description = "Users last date online",
                    name = "lastDateOnOnline",
                    type = "string")
            @RequestParam(required = false)
            LocalDateTime lastDateOnOnline,
            @Schema(description = "Users role",
                    name = "role",
                    type = "string",
                    example = "USER")
            @RequestParam(required = false)
            String role) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAllWithPagination(page,
                        perPage,
                        recordState,
                        createDate,
                        lastDateOnOnline,
                        role));
    }
}