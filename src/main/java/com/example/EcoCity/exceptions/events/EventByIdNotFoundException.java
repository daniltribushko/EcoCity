package com.example.EcoCity.exceptions.events;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 */
public class EventByIdNotFoundException extends GlobalAppException {
    public EventByIdNotFoundException(Long id) {
        super(404, "Event with " + id + " not found");
    }
}
