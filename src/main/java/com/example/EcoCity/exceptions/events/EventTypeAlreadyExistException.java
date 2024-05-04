package com.example.EcoCity.exceptions.events;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 */
public class EventTypeAlreadyExistException extends GlobalAppException {
    public EventTypeAlreadyExistException(String name) {
        super(409, "District: " + name + " already exist");
    }
}
