package com.example.EcoCity.exceptions.events;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 03.05.2024
 */
public class ParticipantsMoreMaxCountException extends GlobalAppException {
    public ParticipantsMoreMaxCountException() {
        super(400, "Participants more then max count");
    }
}
