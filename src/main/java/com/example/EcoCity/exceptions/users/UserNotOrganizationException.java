package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 */
public class UserNotOrganizationException extends GlobalAppException {
    public UserNotOrganizationException(String email) {
        super(404, "User " + email + " not organization");
    }
}
