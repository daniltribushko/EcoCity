package com.example.EcoCity.exceptions.events;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 03.05.2024
 */
public class EventHasMaxParticipantsException extends GlobalAppException {
    public EventHasMaxParticipantsException(Long id) {
        super(409, "Event with " + id + " has max participants");
    }
}
