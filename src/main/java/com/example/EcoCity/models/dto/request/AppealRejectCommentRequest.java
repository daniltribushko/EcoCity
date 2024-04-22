package com.example.EcoCity.models.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Tribushko Danil
 * @since 21.04.2024
 * <p>
 * Dto запроса на работу с комменатрием к отклонению обращения
 */
public class AppealRejectCommentRequest {
    @NotBlank(message = "Message can not be blank")
    private String message;

    public AppealRejectCommentRequest(String message) {
        this.message = message;
    }

    public AppealRejectCommentRequest() {
    }

    public String getMessage() {
        return message;
    }
}
