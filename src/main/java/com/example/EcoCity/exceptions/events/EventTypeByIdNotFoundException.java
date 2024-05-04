package com.example.EcoCity.exceptions.events;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.04.2024
 */
public class EventTypeByIdNotFoundException extends GlobalAppException {
    public EventTypeByIdNotFoundException(Integer id) {
        super(404, "Event type with " + id + " id not found");
    }
}
