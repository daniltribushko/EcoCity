package com.example.EcoCity.exceptions.events;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 */
public class EventAlreadyExistException extends GlobalAppException {
    public EventAlreadyExistException(String name) {
        super(409, "Event " + name + " already exist");
    }
}
