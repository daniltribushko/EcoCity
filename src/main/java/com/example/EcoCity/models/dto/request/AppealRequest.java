package com.example.EcoCity.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 * <p>
 * Dto запроса на работу с обращениями
 */
public class AppealRequest {
    @Schema(description = "Appeal text",
            name = "text",
            type = "string",
            example = "Example text")
    @NotBlank(message = "Text can not be blank")
    private String text;

    @Schema(description = "Appeal address",
            name = "address",
            type = "string",
            example = "Home 1 street 1 kv 1")
    @NotBlank(message = "Address can not be blank")
    private String address;

    public AppealRequest(String text, String address) {
        this.text = text;
        this.address = address;
    }

    public AppealRequest() {
    }

    public String getText() {
        return text;
    }

    public String getAddress() {
        return address;
    }
}
