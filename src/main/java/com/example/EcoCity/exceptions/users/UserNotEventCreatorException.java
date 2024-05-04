package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 02.05.2024
 */
public class UserNotEventCreatorException extends GlobalAppException {
    public UserNotEventCreatorException(String email, Long id) {
        super(403, "User " + email + " not creator event with " + id + " id");
    }
}
