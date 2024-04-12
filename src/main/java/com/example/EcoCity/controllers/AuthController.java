package com.example.EcoCity.controllers;

import com.example.EcoCity.models.dto.request.SignInRequest;
import com.example.EcoCity.models.dto.request.SignUpRequest;
import com.example.EcoCity.models.dto.response.ExceptionResponse;
import com.example.EcoCity.models.dto.response.JwtTokenResponse;
import com.example.EcoCity.models.dto.response.UserResponse;
import com.example.EcoCity.services.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Контроллер для авторизации и регистрации пользователей
 */
@Tag(name = "Auth Controller")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUserService authUserService;

    @Autowired
    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Operation(summary = "Sign-in", description = "Issuing a token to a registered user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtTokenResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Wrong email or password",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation exception",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> signIn(@Valid
                                                   @RequestBody
                                                   SignInRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authUserService.signIn(request));
    }

    @Operation(summary = "Sign-up", description = "Registration a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User is registered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtTokenResponse.class))),
            @ApiResponse(responseCode = "409", description = "User by email bor found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation exception",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Password mismatch",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@Valid
                                               @RequestBody
                                               SignUpRequest request) {
        UserResponse response = authUserService.signUp(request);
        return ResponseEntity.created(URI.create("api/user/" + response.getId()))
                .body(response);
    }
}
