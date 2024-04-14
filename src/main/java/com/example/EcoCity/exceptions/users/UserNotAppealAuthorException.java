package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 */
public class UserNotAppealAuthorException extends GlobalAppException {
    public UserNotAppealAuthorException(String email, Long id) {
        super(403, "User " + email + " not author appeal with " + id + " id");
    }
}
