package com.example.EcoCity.models.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 * <p>
 * Dto ответа токена
 */
public class JwtTokenResponse {
    @Schema(description = "User jwt token",
            name = "token",
            type = "string",
            example = "testToken")
    private String token;

    public JwtTokenResponse(String token){
        this.token = token;
    }

    public JwtTokenResponse(){}

    public String getToken(){
        return token;
    }
}
