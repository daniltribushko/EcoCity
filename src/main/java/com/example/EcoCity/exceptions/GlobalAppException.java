package com.example.EcoCity.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 29.03.2024
 *
 * Класс исклюений приложения
 */
public abstract class GlobalAppException extends RuntimeException {
    protected final int status;
    protected final String message;
    protected final LocalDateTime timesTamp;
    private final Logger logger = LoggerFactory.getLogger(GlobalAppException.class);
    protected GlobalAppException(int status, String message){
        this.status = status;
        this.message = message;
        timesTamp = LocalDateTime.now();
        String logString = status + " " + message;
        logger.warn(logString);
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimesTamp() {
        return timesTamp;
    }
}
