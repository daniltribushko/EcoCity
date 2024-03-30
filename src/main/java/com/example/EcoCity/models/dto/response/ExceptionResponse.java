package com.example.EcoCity.models.dto.response;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Dto ответа исключения
 */
public class ExceptionResponse {
    private int status;
    private LocalDateTime timesTamp;
    private String message;

    public ExceptionResponse(int status, LocalDateTime timesTamp, String message) {
        this.status = status;
        this.timesTamp = timesTamp;
        this.message = message;
    }

    public ExceptionResponse() {
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimesTamp() {
        return timesTamp;
    }

    public String getMessage() {
        return message;
    }
}
