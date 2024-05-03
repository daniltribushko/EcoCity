package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko danil
 * @since 03.05.2024
 */
public class UserAlreadyParticipantException extends GlobalAppException {
    public UserAlreadyParticipantException(String email, Long id) {
        super(409, "User " + email + " already event " + id + " participant");
    }
}
