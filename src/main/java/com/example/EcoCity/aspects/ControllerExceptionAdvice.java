package com.example.EcoCity.aspects;

import com.example.EcoCity.exceptions.GlobalAppException;
import com.example.EcoCity.models.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Контроллер для перехвата исключений
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(GlobalAppException.class)
    public ResponseEntity<ExceptionResponse> handleGlobalAppException(GlobalAppException exception) {
        int status = exception.getStatus();
        return ResponseEntity.status(status)
                .body(new ExceptionResponse(status,
                        exception.getTimesTamp(),
                        exception.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(new ExceptionResponse(status.value(),
                        LocalDateTime.now(),
                        exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status)
                .body(new ExceptionResponse(status.value(),
                        LocalDateTime.now(),
                        exception.getMessage()));
    }
}
